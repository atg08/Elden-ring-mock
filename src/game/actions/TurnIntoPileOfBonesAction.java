package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.gameactors.enemies.graveyard.PileOfBones;
import game.gameactors.enemies.graveyard.Skeleton;


/**

 A class that represents an action where a Skeleton actor turns into a PileOfBones actor.
 *
 * @author Tanul , Satoshi , Aditti
 * @version 1.0.0
 */
public class TurnIntoPileOfBonesAction extends Action {

    /**
     * Executes the action of turning a Skeleton actor into a PileOfBones actor by removing the Skeleton actor from its location
     * on the GameMap and adding a PileOfBones actor to the same location.
     *
     * @param actor the Skeleton actor that is to be turned into a PileOfBones actor
     * @param map the GameMap on which the action is to be executed
     * @return a String message indicating that the Skeleton actor has turned into a PileOfBones actor
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // note the actor must be Skeleton

        // remove the HSS
        Location location = map.locationOf(actor);
        map.removeActor(actor);

        map.addActor(new PileOfBones((Skeleton) actor), location);

        return actor.toString() + "turns into a Pile Of Bones";
    }


    /**
     * Returns the menu description of the action.
     *
     * @param actor the actor that is to execute the action
     * @return null as there is no menu description for this action
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
