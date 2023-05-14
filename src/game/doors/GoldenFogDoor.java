package game.doors;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.World;
import game.actions.AttackAction;
import game.actions.TeleportAction;
import game.gameactors.StatusActor;
import game.gameactors.players.Player;

public class GoldenFogDoor extends Ground {

    private final int LIMGRAVE_MAP = 0;
    private final int STORMVEIL_MAP = 1;
    private final int ROUNDTABLE_MAP = 2;
    /**
     * Constructor.
     */
    public GoldenFogDoor() {
        super('D');
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(StatusActor.IS_PLAYER);
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();


    // door in roundtableDoor is at 10,10

        // add teleport action if player
        // check location of player and based on that present option
        if (actor.hasCapability(StatusActor.IS_PLAYER)) {
            // for intrinsic weapon
            actions.add(new TeleportAction());
            return actions;
        }
        return actions;
    }
}
