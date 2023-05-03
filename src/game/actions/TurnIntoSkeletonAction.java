package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.gameactors.enemies.Revivable;
import game.gameactors.enemies.graveyard.PileOfBones;
import game.gameactors.enemies.graveyard.Skeleton;


/**
 * Represents an Action that turns a PileOfBones into a Skeleton.
 *
 * @author Tanul , Satoshi , Aditti
 * @version 1.0.0
 */
public class TurnIntoSkeletonAction extends Action {


    /**
     * Executes the action of turning a PileOfBones into a Skeleton and reviving it.
     *
     * @param actor the PileOfBones to be turned into a Skeleton
     * @param map   the GameMap containing the actor
     * @return a String indicating that the actor has been revived to a Skeleton
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        PileOfBones pob = (PileOfBones) actor;
        Skeleton target = pob.getReviveBackTo();
        Revivable reviveTarget = (Revivable) target;
        Skeleton revived = (Skeleton) reviveTarget.revive();
        Location whereToRevive = map.locationOf(actor);
        map.removeActor(actor);
        map.addActor(revived, whereToRevive);
        return actor + "has been revived to " + revived;
    }


    /**
     * Returns the description of the action, which is null for this implementation.
     *
     * @param actor the actor performing the action
     * @return null, as there is no description for this action
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
