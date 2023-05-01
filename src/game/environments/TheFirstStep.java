package game.environments;

import edu.monash.fit2099.engine.positions.Location;
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

//    @Override
//    public void tick(Location location) {
////        super.tick(location);
//        this.siteLocation = location;
//    }

}
