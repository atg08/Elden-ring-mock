package game.environments.siteoflostgrace;

/**
 * TheFirstStep class extends the SiteOfLostGrace class and represents the first step in a process.
 * It implements the singleton design pattern.
 *
 * @author Tanul
 * @version 1.0
 * @see SiteOfLostGrace
 */
public class TheFirstStep extends SiteOfLostGrace {

    private static TheFirstStep instance;

    /**
     * Constructor.

     */
    public TheFirstStep() {
        super();
        this.setActivated(true);
        this.setDiscovered(true);
    }


    /**

     Get the singleton instance of TheFirstStep.
     @return the instance of TheFirstStep
     */
    public static TheFirstStep getInstance(){
        if (instance == null){
            instance = new TheFirstStep();
        }
        return instance;
    }


}
