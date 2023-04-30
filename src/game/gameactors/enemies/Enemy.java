package game.gameactors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Reset.ResetManager;
import game.Reset.Resettable;
import game.actions.AttackAction;
import game.actions.DespawnAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.WanderBehaviour;
import game.gameactors.EnemyType;
import game.gameactors.StatusActor;
import game.runes.Rune;
import game.utils.RandomNumberGenerator;
import game.weapons.WeaponSkill;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public abstract class Enemy extends Actor implements Resettable {
    protected ResetManager rm;
    protected StatusActor enemyType;
    protected Map<Integer, Behaviour> behaviours = new TreeMap<>();
    protected int despawnRate = 10;
    protected int maxRuneDrop;
    protected int minRuneDrop;

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Enemy(String name, char displayChar, int hitPoints, int minRuneDrop, int maxRuneDrop) {
        super(name, displayChar, hitPoints);
        addCapability(StatusActor.IS_ENEMY);
        rm.registerResettable(this);

        this.maxRuneDrop = maxRuneDrop;
        this.minRuneDrop = minRuneDrop;

        // wander bahavior
        this.behaviours.put(999, new WanderBehaviour());

        // TODO for other bahaviors, add manually through the Application class

    }

    /**
     * At each turn, select a valid action to perform.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (!this.hasCapability(StatusActor.FOLLOWING_PLAYER) && RandomNumberGenerator.getBooleanProbability(this.despawnRate)){
            return new DespawnAction();
        }

        // reset following status
        this.removeCapability(StatusActor.FOLLOWING_PLAYER);

        // put behaviors into actions
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;
        }
        return new DoNothingAction();
    }

    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions =  new ActionList();

        // add targeted attack if player
        if (otherActor.hasCapability(StatusActor.HOSTILE_TO_ENEMY)){

            // for intrinsic weapon
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

    public Rune getDeathRune(){
        return new Rune(RandomNumberGenerator.getRandomIntInRange(this.minRuneDrop, this.maxRuneDrop));
    }

    public boolean canTarget(Actor otherActor){

        // if otherActor is a player, Enemy can attack him
        if (otherActor.hasCapability(StatusActor.HOSTILE_TO_ENEMY)){
            return true;
        }

        // if otherActor is an enemy of not same type, Enemy can attack it
        List<EnemyType> actorTypeList = this.findCapabilitiesByType(EnemyType.class);
        EnemyType actorType = actorTypeList.get(0); // the type we are looking for

        List<EnemyType> otherActorTypeList = otherActor.findCapabilitiesByType(EnemyType.class);
        EnemyType otherActorType = otherActorTypeList.get(0);

        return !actorType.equals(otherActorType);
    }

    public IntrinsicWeapon getIntrinsicWeapon() {
        return null;
    }

    public StatusActor getEnemyType(){
        return this.enemyType;
    }

    @Override
    public String reset(Actor actor, GameMap map) {
        DespawnAction despawn = new DespawnAction();
        return despawn.execute(this, map);
    }

    public int getMinRune(){
        return this.minRuneDrop;
    }

    public int getMaxRune(){
        return this.maxRuneDrop;
    }
}
