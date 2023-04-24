package game.environments;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.RestAction;
import game.gameactors.StatusActor;

public class SiteOfLostGrace extends Ground {

    private GameMap map;
    /**
     * Constructor.
     */
    public SiteOfLostGrace() {
        super('U');
    }

    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = new ActionList();
        if (actor.hasCapability(StatusActor.CAN_REST)){
            actions.add(new RestAction());
        }
        return actions;
    }

}
