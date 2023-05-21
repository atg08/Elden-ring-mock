package game.environments.siteoflostgrace;

/**
 * The StormveilMainGate class represents the main gate of the Stormveil location.
 * It extends the SiteOfLostGrace class.
 *
 * @author Tanul, Satoshi, Aditti
 * @version 1.0
 * @see SiteOfLostGrace
 */
public class StormveilMainGate extends SiteOfLostGrace {

    private static StormveilMainGate instance;

    /**
     * Constructs a new instance of the StormveilMainGate class.
     */
    public StormveilMainGate() {
        super();
    }

    /**
     * Retrieves the singleton instance of StormveilMainGate.
     *
     * @return the singleton instance of StormveilMainGate
     */
    public static StormveilMainGate getInstance(){
        if (instance == null){
            instance = new StormveilMainGate();
        }
        return instance;
    }
}
