package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.gameactors.players.Player;
import game.items.Rune;


/**
 * The DropRuneAction class represents an action where a player drops a rune at their previous location.
 * This action should only be used for players.
 *
 * @author Tanul, Satoshi, Aditti
 * @version 1.0
 * @see Action
 */
public class DropRuneAction extends Action {

    /**
     * drops rune at player's previous location
     * should be used only for player
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A string representing the result of the action.
     * @see Actor
     * @see GameMap
     */
    @Override
    public String execute(Actor actor, GameMap map){
        Player player = (Player) actor;
        Location playerPreviousLocation = player.getPlayerPreviousLocation();

        Rune droppedRune = player.getDeathRune();
        droppedRune.setRuneLocation(playerPreviousLocation);

        playerPreviousLocation.addItem(droppedRune);
        return actor + " dropped " + droppedRune.getAmount();
    }


    /**
     * Returns the description of the action for the menu.
     *
     * @param actor The actor performing the action.
     * @return The description of the action for the menu.
     * @see Actor
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
