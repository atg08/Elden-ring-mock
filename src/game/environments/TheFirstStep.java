package game.environments;

import game.Reset.ResetManager;

public class TheFirstStep extends SiteOfLostGrace{

    private static TheFirstStep instance;

    /**
     * Constructor.

     */
    public TheFirstStep() {
        super();
    }

    public static TheFirstStep getInstance(){
        if (instance == null){
            instance = new TheFirstStep();
        }
        return instance;
    }

}
