package game.environments;

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
