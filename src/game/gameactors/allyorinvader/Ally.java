package game.gameactors.allyorinvader;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.DespawnAction;
import game.gameactors.StatusActor;
import game.gameactors.players.Player;
import game.reset.ResetManager;
import game.reset.Resettable;

/**
 * The Ally class represents an allied actor in the game.
 * It extends the Actor class and implements the Resettable interface.
 * Allies provide support to the player.
 *
 * @version 1.0
 * @see Actor
 * @see Resettable
 */
public class Ally extends Actor implements Resettable {

    private ResetManager rm = ResetManager.getInstance();

    /**
     * Constructs an Ally object.
     *
     * @param player the player associated with the ally
     */
    public Ally(Player player) {
        super("Ally", 'A', player.getMaxHP());
        this.addCapability(StatusActor.IS_ALLY);
        this.addWeaponToInventory(player.getWeaponInventory().get(0)); // all players have one weapon in inventory
        rm.registerResettable(this);
    }

    /**
     * Plays a turn for the ally.
     *
     * @param actions    the list of possible actions
     * @param lastAction the last action performed
     * @param map        the current game map
     * @param display    the display object for rendering
     * @return the chosen action for the ally's turn
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return null;
    }


    /**
     * Resets the ally when triggered by an event.
     *
     * @param actor the actor triggering the reset
     * @param map   the current game map
     * @return a descriptive string indicating the reset action
     * @see DespawnAction
     */
    // TODO: 5/14/2023 make it so that not removed on rest but only on players death
    @Override
    public String reset(Actor actor, GameMap map) {
        DespawnAction despawn = new DespawnAction();
        return despawn.execute(this, map);
    }

    /**
     * Checks if the ally is removable from the game.
     *
     * @return true if the ally is removable, false otherwise
     */
    @Override
    public boolean isRemovable() {
        return true;
    }

//    @Override
//    public boolean isRemovableOnPlayerRest() {
//        return false;
//    }
}
