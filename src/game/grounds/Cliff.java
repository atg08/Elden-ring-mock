package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.DeathAction;
import game.gameactors.StatusActor;


/**
 * The Cliff class represents a type of Ground that acts as a cliff.
 * It extends the Ground class.
 *
 * @author Tanul, Satoshi, Aditti
 * @version 1.0
 * @see Ground
 */
public class Cliff extends Ground {


    /**
     * Constructor for creating a Cliff object.
     * It calls the superclass constructor with the specified symbol.
     */
    public Cliff() {
        super('+');
    }

    /**
     * Performs a tick operation for the Cliff object.
     * The Ground can also experience the joy of time.
     *
     * @param location The location of the Ground.
     * @see Location
     */
    public void tick(Location location) {
        // if player -> trigger death action
        Actor actor = location.getActor();
        if (actor != null && actor.hasCapability(StatusActor.IS_PLAYER)){
            String result = new DeathAction(actor).execute(actor, location.map());
            new Display().println(result);
        }
    }
}
