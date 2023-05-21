package game.gameactors.allyorinvader;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.DespawnAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;
import game.gameactors.StatusActor;
import game.gameactors.enemies.NPC;
import game.reset.ResetManager;
import game.reset.Resettable;

/**
 * The Ally class represents an ally NPC that can follow the player and perform actions in the game.
 * It extends the NPC class and implements the Resettable and IFollowable interfaces.
 *
 * @author Tanul, Satoshi, Aditti
 * @version 1.0
 * @see NPC
 * @see Resettable
 */
public class Ally extends NPC implements Resettable {

    private ResetManager rm = ResetManager.getInstance();

    /**
     * Constructs an Ally object with the specified hit points and weapon.
     *
     * @param hitPoints the initial hit points of the ally
     * @param weapon the weapon item of the ally
     */
    public Ally(int hitPoints, WeaponItem weapon) {
        super("Ally", 'A', hitPoints);
        this.addCapability(StatusActor.IS_ALLY);
        this.addCapability(StatusActor.HOSTILE_TO_ENEMY);
        this.addCapability(StatusActor.IS_DEATH_RUNE_DROPPER);
        this.addWeaponToInventory(weapon); // all players have one weapon in inventory
        rm.registerResettable(this);

        // add  behaviours
        this.behaviours.put(1, new AttackBehaviour(NPC.player));
        this.behaviours.put(3, new WanderBehaviour());
    }


    /**
     * Plays a turn for the ally, including recording the ally's location.
     *
     * @param actions the list of possible actions
     * @param lastAction the last action performed
     * @param map the game map
     * @param display the display object for the game
     * @return the action to be performed by the ally
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        return super.playTurn(actions, lastAction, map, display);
    }


    /**
     * Checks if the ally can target the specified actor.
     *
     * @param subject the actor to be checked
     * @return true if the ally can target the actor, false otherwise
     */
    @Override
    public boolean canTarget(Actor subject) {
        return subject.hasCapability(StatusActor.HOSTILE_TO_PLAYER);
    }



    /**
     * Resets the ally's state, either by despawning or keeping it alive based on the "rest" parameter.
     *
     * @param map the game map
     * @param rest true if the player is resting, false otherwise
     * @return a string indicating the result of the reset operation
     */
    @Override
    public String reset(GameMap map, boolean rest) {
        if (!rest) {
            DespawnAction despawn = new DespawnAction();
            return despawn.execute(this, map);
        }
        return this + " is not despawned";
    }

    /**
     * Checks if the ally is removable.
     *
     * @return true if the ally is removable, which is always the case
     */
    @Override
    public boolean isRemovable() {
        return true;
    }


    /**
     * Checks if the ally is removable when the player rests.
     *
     * @return true if the ally is removable when the player rests, false otherwise
     */
    public boolean isRemovableOnPlayerRest() {
        return false;
    }

}
