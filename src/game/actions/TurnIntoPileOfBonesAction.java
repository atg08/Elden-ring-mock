package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.gameactors.enemies.graveyard.PileOfBones;
import game.gameactors.enemies.graveyard.Skeleton;

public class TurnIntoPileOfBonesAction extends Action {
    @Override
    public String execute(Actor actor, GameMap map) {
        // note: the actor must be Skeleton

        // remove the HSS
        Location location = map.locationOf(actor);
        map.removeActor(actor);

        map.addActor(new PileOfBones((Skeleton) actor), location);


        return actor.toString() + "turns into a Pile Of Bones";
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
