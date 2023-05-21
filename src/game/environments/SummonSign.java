package game.environments;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.SummonAction;
import game.gameactors.StatusActor;

/**
 * Environment to summon ally or invader
 */
public class SummonSign extends Ground {
    /**
     * Constructor.
     *
     */
    public SummonSign() {
        super('=');
    }

    /**
     * Returns a list of allowable actions for the specified actor at the given location in the specified direction.
     *
     * @param actor     The actor performing the action.
     * @param location  The location where the action is being performed.
     * @param direction The direction in which the action is being performed.
     * @return A list of allowable actions for the actor at the specified location and direction.
     * @see ActionList
     */
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = new ActionList();
        if (actor.hasCapability(StatusActor.IS_PLAYER)){
            actions.add(new SummonAction());

        }
        return actions;
    }
}
