package game.environments;

import edu.monash.fit2099.engine.positions.Location;
import game.gameactors.players.Player;

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
