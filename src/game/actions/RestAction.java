package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Reset.ResetManager;

public class RestAction extends Action {

    private ResetManager rm;

    /**
     * When called it will call the ResetManager to perform the reset of the game
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        rm.run(map);
        return actor + "has rested at a site of lost grace";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + "has rested at a site of lost grace";
    }
}
