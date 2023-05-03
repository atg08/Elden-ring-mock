package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;


/**
 * A class that represents the action of despawning an actor from a game map.
 *
 * @author Tanul , Satoshi , Aditti
 * @version 1.0.0
 */
public class DespawnAction extends Action {


    /**
     * Executes the action of despawning the actor from the given game map.
     *
     * @param actor   The actor to be despawned.
     * @param gameMap The game map from which the actor should be despawned.
     * @return A string representing the description of the action.
     */
    @Override
    public String execute(Actor actor, GameMap gameMap) {
        gameMap.removeActor(actor);
        return this.menuDescription(actor);
    }


    /**
     * Returns a string representing the description of the action.
     *
     * @param actor The actor being despawned.
     * @return A string representing the description of the action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " is despawned.";
    }
}
