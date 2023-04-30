package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Reset.ResetManager;
import game.gameactors.StatusActor;
import game.utils.RandomNumberGenerator;

public class DespawnAction extends Action {

    @Override
    public String execute(Actor actor, GameMap gameMap) {
        gameMap.removeActor(actor);
        return this.menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + "is despawned.";
    }
}
