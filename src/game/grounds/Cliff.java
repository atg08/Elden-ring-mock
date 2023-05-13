package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.DeathAction;
import game.gameactors.StatusActor;

public class Cliff extends Ground {
    /**
     * Constructor.
     */
    public Cliff() {
        super('+');
    }

    /**
     * Ground can also experience the joy of time.
     * @param location The location of the Ground
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

