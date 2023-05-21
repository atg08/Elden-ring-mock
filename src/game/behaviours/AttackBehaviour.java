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
import game.gameactors.enemies.NPC;
import game.gameactors.players.Player;
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
    private final Player player;

    public AttackBehaviour(Player player){
        this.player = player;
    }



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
        if (actor.hasCapability(StatusActor.FOLLOWING) && map.locationOf(this.player) != this.player.getPlayerPreviousLocation()){
            return null;
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
        if (!this.shouldDoTargetedAttack()){
            return this.getAreaAttackAction(actor, map, weapon);
        }
        // if targeted action is chosen randomly but there wasn't an enemy that he can attack -> try area attack
        Action action = getTargetedAction(actor, map, weapon);
        if (action == null){
            return this.getAreaAttackAction(actor, map, weapon);
        }
        return action;
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
    private boolean shouldDoTargetedAttack(){
        return RandomNumberGenerator.getTrueOrFalse();
    }
}
