package game.environments;

public class StormveilMainGate extends SiteOfLostGrace{

    private static StormveilMainGate instance;

    /**
     * Constructor.

     */
    public StormveilMainGate() {
        super();
    }

    /**

     Get the singleton instance of TheFirstStep.
     @return the instance of TheFirstStep
     */
    public static StormveilMainGate getInstance(){
        if (instance == null){
            instance = new StormveilMainGate();
        }
        return instance;
    }
}
