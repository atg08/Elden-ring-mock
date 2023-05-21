package game.doors;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.TeleportAction;
import game.gameactors.StatusActor;


/**
 * The GoldenFogDoor class represents a special type of Ground called a golden fog door.
 * It extends the Ground class and implements specific behavior for this type of door.
 *
 * @author Tanul, Satoshi, Aditti
 * @version 1.0
 * @see Ground
 */
public class GoldenFogDoor extends Ground {

    /**
     * Constructor for the GoldenFogDoor class.
     * Initializes the door with the character 'D'.
     */
    public GoldenFogDoor() {
        super('D');
    }


    /**
     * Checks if an actor can enter the GoldenFogDoor.
     *
     * @param actor The actor attempting to enter the door.
     * @return True if the actor has the capability of being a player (StatusActor.IS_PLAYER),
     *         indicating they can enter the door. False otherwise.
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(StatusActor.IS_PLAYER);
    }


    /**
     * Returns a list of allowable actions for an actor at the specified location and direction.
     *
     * @param actor     The actor for which the actions are being checked.
     * @param location  The current location of the actor.
     * @param direction The direction the actor is facing.
     * @return An ActionList object containing the allowable actions for the actor.
     *         If the actor is a player, a TeleportAction is added to the list.
     *         Otherwise, an empty list is returned.
     */
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
