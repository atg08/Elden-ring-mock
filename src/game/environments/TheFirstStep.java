package game.environments;

/**

 A site of lost grace representing the first step in the game.
 @author tanul
 */
public class TheFirstStep extends SiteOfLostGrace{

    private static TheFirstStep instance;

    /**
     * Constructor.

     */
    public TheFirstStep() {
        super();
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
