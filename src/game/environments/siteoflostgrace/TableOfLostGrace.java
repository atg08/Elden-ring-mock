package game.environments.siteoflostgrace;

/**
 * The TableOfLostGrace class represents a table of lost grace.
 * It is a singleton class that extends the SiteOfLostGrace class.
 *
 * @author Tanul, Satoshi, Aditti
 * @version 1.0
 * @see SiteOfLostGrace
 */
public class TableOfLostGrace extends SiteOfLostGrace {
    private static TableOfLostGrace instance;

    /**
     * Constructs a new TableOfLostGrace object.
     */
    public TableOfLostGrace() {
        super();
    }

    /**
     * Returns the singleton instance of the TableOfLostGrace class.
     *
     * @return the instance of TableOfLostGrace
     */
    public static TableOfLostGrace getInstance(){
        if (instance == null){
            instance = new TableOfLostGrace();
        }
        return instance;
    }

}
