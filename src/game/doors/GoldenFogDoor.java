package game.doors;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.gameactors.StatusActor;

public class GoldenFogDoor extends Ground {
    /**
     * Constructor.
     */
    public GoldenFogDoor() {
        super('D');
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        // add teleport action if player
        // check location of player and based on that present option
        if (actor.hasCapability(StatusActor.IS_PLAYER)) {
            // for intrinsic weapon
//            actions.add(new TeleportAction());
            return actions;
        }
        return actions;
    }
}
