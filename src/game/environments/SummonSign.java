package game.environments;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.SummonAction;
import game.gameactors.StatusActor;

public class SummonSign extends Ground {
    /**
     * Constructor.
     *
     */
    public SummonSign() {
        super('=');
    }

    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = new ActionList();
        if (actor.hasCapability(StatusActor.IS_PLAYER)){
            actions.add(new SummonAction());

        }
        return actions;
    }
}
