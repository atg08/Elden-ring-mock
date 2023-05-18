package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AreaAttackAction;
import game.actions.AttackAction;
import game.gameactors.StatusActor;
import game.gameactors.enemies.IFollowable;
import game.gameactors.enemies.IFollower;
import game.gameactors.enemies.NPC;
import game.utils.RandomNumberGenerator;
import game.weapons.WeaponSkill;

import java.util.ArrayList;
import java.util.Random;


/**

 * This class implements the Behaviour interface, which represents the behavior of an actor to attack other actors.
 * The AttackBehaviour is responsible for selecting and executing the appropriate action for attacking.
 *
 * @author Tanul , Satoshi , Aditti
 * @version 1.0.0
 */
public class AttackBehaviour implements Behaviour {

    private final Random random = new Random();


    /**
     * This method returns the appropriate action for attacking an enemy actor.
     *
     * first choose intrinsic weapon/weaponitem; then choose targetedattack/areaattack
     *
     * @param actor the actor to perform the action.
     * @param map the map that the actor is in.
     * @return the action to attack the enemy actor.
     */
    // assume enemy does not own any weapons with a special skill (like Uchigatana)
    @Override
    public Action getAction(Actor actor, GameMap map) {
        // note: technically this actor should always be an NPC

        // if NPC is following another actor already
        // --> need to continue following if the target has no moved
        // if target has not moved in this turn, then attack the target (not any other actors even if there is)
        if (actor.hasCapability(StatusActor.FOLLOWING)){
           return this.getActionIfHaveBeenFollowing(actor, map);
        }

        // NPC must have at least one weapon to attack
        if(this.NPCHasNoWeapon(actor)){
            return null;
        }

        WeaponItem weapon = this.getWeaponForAttacking(actor);

        // decide if the NPC's weapon can make area/targeted attack
        // note: we assume that all enemy intrinsic weapons can do both targeted and area attack
        boolean canTargetAttack = true;
        boolean canAreaAttack = true;

        if (weapon != null){
            canAreaAttack = weapon.hasCapability(WeaponSkill.AREA_ATTACK);
            canTargetAttack = weapon.hasCapability(WeaponSkill.TARGETED_ATTACK);
        }

        // if this NPC can only make targeted/area attack -> do that
        if (canTargetAttack && !canAreaAttack){
            return this.getTargetedAction(actor, map, weapon);
        } else if (!canTargetAttack && canAreaAttack){
            return this.getAreaAttackAction(actor, map, weapon);
        }

        // if NPC can make both targeted and area attack, choose one of them randomly
        if (!this.shouldUseTargetedAttack()){
            return this.getAreaAttackAction(actor, map, weapon);
        }
        // if targeted action is chosen randomly but there wasn't an enemy that he can attack -> try area attack
        Action action = getTargetedAction(actor, map, weapon);
        if (action == null){
            return this.getAreaAttackAction(actor, map, weapon);
        }
        return action;
    }

    private Action getActionIfHaveBeenFollowing(Actor actor, GameMap map){
        // check if player has moved or not
        IFollower follower = (IFollower) actor;
        IFollowable followable = (IFollowable) follower.getFollowingActor();
        Location previousLocation = followable.getPlayerPreviousLocation();
        Location currentLocation = map.locationOf((Actor)followable);

        // need to ensure that this player is still in the exits
        // (if there is a ground where this NPC cannot enter, player might have moved somewhere)
        if (!follower.isFollowingActorInExits(map.locationOf(actor).getExits())){
            return null;
        }

        // the NPC still needs to follow this enemy
        if (previousLocation != currentLocation){
            return null;
        }

        // remove the following status since the NPC has attacked once
        follower.resetFollowingStatus();

        // the NPC cannot attack if he does not have a weapon
        if (this.NPCHasNoWeapon(actor)){
            return null;
        }

        // check if NPC has weaponItem or intrinsic weapon with TARGETED_ATTACK capability
        boolean hasWeaponItem = actor.getWeaponInventory().size() > 0 &&
                actor.getWeaponInventory().get(0).hasCapability(WeaponSkill.TARGETED_ATTACK);
        boolean hasIntrinsicWeapon = actor.getIntrinsicWeapon() != null;

        // if NPC only has one or zero weapons
        if (hasWeaponItem && !hasIntrinsicWeapon){
            return getTargetedAction(actor, map, actor.getWeaponInventory().get(0));
        }else if (!hasWeaponItem && hasIntrinsicWeapon){
            return getTargetedAction(actor, map, null);
        }else if (!hasWeaponItem && !hasIntrinsicWeapon){
            return null;
        }

        // if NPC has both of them
        if(this.shouldUseIntrinsicWeapon()){
            return this.getTargetedAction(actor, map, null);
        }
        return this.getTargetedAction(actor, map, actor.getWeaponInventory().get(0));
    }

    private boolean NPCHasNoWeapon(Actor actor){
        boolean hasWeaponItem = actor.getWeaponInventory().size() > 0;
        boolean hasIntrinsicWeapon = actor.getIntrinsicWeapon() != null;

        return !hasWeaponItem && !hasIntrinsicWeapon;
    }

    /**
     *
     * @param actor
     * @return null if intrinsic weapon; Weapon if there is an weaponItem in actor's inventory
     */
    private WeaponItem getWeaponForAttacking(Actor actor){
        // choose which weapon to use for attacking
        boolean hasWeaponItem = actor.getWeaponInventory().size() > 0;
        boolean hasIntrinsicWeapon = actor.getIntrinsicWeapon() != null;
        WeaponItem weapon;


        if (hasWeaponItem && !hasIntrinsicWeapon){
            weapon = actor.getWeaponInventory().get(0);
        }else if (!hasWeaponItem){
            weapon = null;
        }else{
            if (this.shouldUseIntrinsicWeapon()){
                weapon = null;
            }else{
                weapon = actor.getWeaponInventory().get(0);
            }
        }

        return weapon;
    }

    private Action getTargetedAction(Actor actor, GameMap map, WeaponItem weaponItem){
        NPC npc = (NPC) actor;
        ArrayList<Action> actions = new ArrayList<>();

        // collects all possible attack-able actors around
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            Actor targetActor = destination.getActor();

            if (targetActor == null){
                continue;
            }

            boolean canAttack = npc.canTarget(targetActor);
            if (canAttack){
                if (weaponItem == null){
                    // intrinsic weapon attack
                    actions.add(new AttackAction(targetActor, exit.getName()));
                }else{
                    // regular weapon attack
                    actions.add(new AttackAction(targetActor, exit.getName(), weaponItem));
                }
            }

        }

        // choosing one action
        if (!actions.isEmpty()) {
            return actions.get(random.nextInt(actions.size()));
        }
        else {
            return null;
        }
    }

    private Action getAreaAttackAction(Actor actor, GameMap map, WeaponItem weaponItem){
        // area attack -> if we find at least one actor (does not need to be atack-able) -> return AreaAttackAction
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            Actor targetActor = destination.getActor();

            if (targetActor == null) {
                continue;
            }

            if (weaponItem != null) {
                // intrinsic weapon attack
                return new AreaAttackAction(weaponItem);
            } else {
                // this method returns AreaAttackAction/special attack
                return new AreaAttackAction();
            }
        }

        return null;
    }
    
    private boolean shouldUseIntrinsicWeapon(){
        return RandomNumberGenerator.getTrueOrFalse();
    }


    /**
     * Determines whether to perform a targeted action or an area attack action.
     *
     * @return true if a targeted action should be performed, false otherwise
     */
    private boolean shouldUseTargetedAttack(){
        return RandomNumberGenerator.getTrueOrFalse();
    }
}
