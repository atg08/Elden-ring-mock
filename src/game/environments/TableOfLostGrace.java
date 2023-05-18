package game.environments;

import game.actions.TeleportAction;

public class TableOfLostGrace extends SiteOfLostGrace{
    private static TableOfLostGrace instance;

    /**
     * Constructor.

     */
    public TableOfLostGrace() {
        super();
    }

    /**

     Get the singleton instance of TheFirstStep.
     @return the instance of TheFirstStep
     */
    public static TableOfLostGrace getInstance(){
        if (instance == null){
            instance = new TableOfLostGrace();
        }
        return instance;
    }

}
