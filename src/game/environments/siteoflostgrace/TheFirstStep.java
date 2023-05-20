package game.environments.siteoflostgrace;

/**

 A site of lost grace representing the first step in the game.
 @author tanul
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
