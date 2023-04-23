package game.Reset;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ResetAction;

import java.util.ArrayList;
import java.util.List;

/**
 * A reset manager class that manages a list of resettables.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class ResetManager {
    private List<Resettable> resettables;
    private static ResetManager instance;

    /**
     * HINT 1: where have we seen a private constructor before?
     * HINT 2: see the instance attribute above.
     */
    private ResetManager() {
        this.resettables = new ArrayList<>();
    }

    public void run(GameMap map) {

        for (Resettable r : resettables){
            ResetAction ra = new ResetAction();
            ra.execute((Actor) r, map); // CAN DO A TRY CATCH IF WRONG CLASS TYPE CAUGHT
                                        // MEANING ITEM OF RESETTABLE
        }
    }

    public void registerResettable(Resettable resettable) {
        this.resettables.add(resettable);
    }

    public void removeResettable(Resettable resettable) {
        this.resettables.remove(resettable);
    }

}
