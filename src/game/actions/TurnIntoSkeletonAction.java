package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.gameactors.enemies.Revivable;
import game.gameactors.enemies.graveyard.PileOfBones;
import game.gameactors.enemies.graveyard.Skeleton;

public class TurnIntoSkeletonAction extends Action {

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

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
