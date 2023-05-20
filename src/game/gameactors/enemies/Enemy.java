package game.gameactors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.reset.ResetManager;
import game.reset.Resettable;
import game.actions.AttackAction;
import game.actions.DespawnAction;
import game.gameactors.EnemyType;
import game.gameactors.StatusActor;
import game.items.Rune;
import game.utils.RandomNumberGenerator;
import game.weapons.WeaponSkill;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**

 * The Enemy class is an abstract class representing a type of actor that can attack and be attacked by other actors.
 * It includes methods to select an action to perform during its turn and to check if it can target another actor.
 *
 * @author Tanul , Satoshi , Aditti
 * @version 1.0.0
 */

public abstract class Enemy extends NPC implements DeathRuneDroppper, IFollower{
    protected ResetManager rm = ResetManager.getInstance();
    protected StatusActor enemyType;
    protected int despawnRate = 10;
    protected int maxRuneDrop;
    protected int minRuneDrop;
    protected IFollowable followingActor;
    protected boolean followingActorAssigned = false;
    protected IFollowable followingActorBuffer = null;

    /**
     * Constructor for the Enemy class.
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     * @param minRuneDrop the minimum number of runes this Enemy drops upon death
     * @param maxRuneDrop the maximum number of runes this Enemy drops upon death
     * @param name        the name of the Actor
     */
    public Enemy(char displayChar, int hitPoints, int minRuneDrop, int maxRuneDrop, String name) {
        super(name, displayChar, hitPoints);
        this.addCapability(StatusActor.IS_ENEMY);
        this.addCapability(StatusActor.HOSTILE_TO_PLAYER);

        this.maxRuneDrop = maxRuneDrop;
        this.minRuneDrop = minRuneDrop;

        this.behaviours.put(1, new FollowBehaviour());
        this.behaviours.put(2, new AttackBehaviour());
        this.behaviours.put(3, new WanderBehaviour());

//        this.followingActor = this.getANewActorToFollow(exits);

    }

    /**
     * Selects a valid action to perform during this Enemy's turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // record enemy's current location
//        this.previousLocation = map.locationOf(this);

        // ensure to store potential actor that can be followed in next turn
        if (!this.followingActorAssigned){
            if (this.followingActorBuffer != null){
                this.followingActor = this.followingActorBuffer;
                this.followingActorAssigned = true;
                this.addCapability(StatusActor.FOLLOWING);
            }else{
                this.followingActorBuffer = this.getANewCandidateActorToFollow(map.locationOf(this).getExits());
            }
        }

        if (!this.hasCapability(StatusActor.FOLLOWING) && RandomNumberGenerator.getBooleanProbability(this.despawnRate)){
            if (this.hasCapability(StatusActor.CAN_DESPAWN)) {
                rm.removeResettable((Resettable) this);
                return new DespawnAction();
            }

        }

        return super.playTurn(actions, lastAction, map, display);
    }


    /**
     * Returns the allowable Actions for this Actor.
     * @param otherActor the other Actor that is being attacked.
     * @param direction the direction in which the attack is happening.
     * @param map the map containing the Actor.
     * @return an ActionList containing the allowable Actions for this Actor.
     */

    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions =  new ActionList();

        // add targeted attack if hostile to enemy
        if (otherActor.hasCapability(StatusActor.HOSTILE_TO_ENEMY)){

            // for intrinsic weapon - assumption: player's intrinsic weapon can only use targeted action
            actions.add(new AttackAction(this, direction));

            // for regular weapons
            for (WeaponItem weaponItem: otherActor.getWeaponInventory()){
                // targeted attack
                if (weaponItem.hasCapability(WeaponSkill.TARGETED_ATTACK)){
                    actions.add(new AttackAction(this, direction, weaponItem));
                }

                // special attack
                Action specialAttack = weaponItem.getSkill(this, direction);
                if (specialAttack != null){
                    actions.add(specialAttack);
                }
            }
        }

        return actions;
    }


    /**

     * Returns the rune of the Enemy Actor after death.
     * @return a Rune object representing the death rune of the Enemy Actor.
     */
    @Override
    public Rune getDeathRune(){
        return new Rune(RandomNumberGenerator.getRandomIntInRange(this.minRuneDrop, this.maxRuneDrop));
    }


    /**
     * Determines if the Enemy can target the given Actor.
     * @param otherActor the Actor to check if it can be targeted by the Enemy.
     * @return true if the Enemy can target the Actor, false otherwise.
     */

    public boolean canTarget(Actor otherActor){

        // if otherActor is a player, Enemy can attack him
        if (otherActor.hasCapability(StatusActor.HOSTILE_TO_ENEMY)){
            return true;
        }

        // if otherActor is an enemy of not same type, Enemy can attack it
        List<EnemyType> actorTypeList = this.findCapabilitiesByType(EnemyType.class);
        List<EnemyType> otherActorTypeList = otherActor.findCapabilitiesByType(EnemyType.class);
        if (actorTypeList.size() == 0 ||  otherActorTypeList.size() == 0){
            return false;
        }

        EnemyType actorType = actorTypeList.get(0); // the type we are looking for
        EnemyType otherActorType = otherActorTypeList.get(0);

        return !actorType.equals(otherActorType);
    }

    /**

     * Returns the intrinsic weapon of the Actor.
     * @return the IntrinsicWeapon object representing the intrinsic weapon of the Enemy Actor.
     */

    public IntrinsicWeapon getIntrinsicWeapon() {
        return null;
    }


    /**

     * Returns the minimum rune that can be dropped by the Enemy Actor.
     * @return the minimum rune that can be dropped.
     */
    public int getMinRune(){
        return this.minRuneDrop;
    }


    /**

     * Returns the maximum rune that can be dropped by the Enemy Actor.
     * @return the maximum rune that can be dropped.
     */
    public int getMaxRune(){
        return this.maxRuneDrop;
    }

    @Override
    public IFollowable getFollowingActor(){
        return this.followingActor;
    }

    @Override
    public boolean canFollowActor(Actor actor){
        return actor.hasCapability(StatusActor.HOSTILE_TO_ENEMY);
    }

//    @Override
//    public void resetFollowingStatus(){
//        this.removeCapability(StatusActor.FOLLOWING);
//        this.followingActor = null;
//    }

    /**
     * Note this method does not yet assign the follwable this enemy will follow. It just returns candidates.
     *
     * @param exits
     * @return
     */

    @Override
    public IFollowable getANewCandidateActorToFollow(List<Exit> exits){
        if (this.followingActorAssigned){
            return this.followingActor;
        }

        ArrayList<IFollowable> actors = new ArrayList<>();

        for (Exit exit: exits){
            Location destination = exit.getDestination();
            Actor actorAtDestination = destination.getActor();
            if (actorAtDestination != null && this.canFollowActor(actorAtDestination)){

                // prioritize player, if there is
                if (actorAtDestination.hasCapability(StatusActor.IS_PLAYER)){
                    return (IFollowable) actorAtDestination;
                }
                actors.add((IFollowable) actorAtDestination);
            }
        }

        if (!actors.isEmpty()) {
           return actors.get(new Random().nextInt(actors.size()));
        }
        else {
            return null;
        }
    }
    @Override
    public boolean isFollowingActorInExits(List<Exit> exits){
        for (Exit exit : exits) {
            Location destination = exit.getDestination();
            Actor targetActor = destination.getActor();

            if (targetActor == this.followingActor){
                return true;
            }

        }
        return false;
    }

    @Override
    public void setFollowingActor(IFollowable followable){
        if(!this.followingActorAssigned){
            this.followingActor = followable;
            this.followingActorAssigned = true;
            this.addCapability(StatusActor.FOLLOWING);
        }
    }

}
