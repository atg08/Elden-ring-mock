package game.items;

/**
 * The PlayerRuneManager class manages the player's runes, including increasing, decreasing, and resetting them.
 *
 * @author Satoshi
 * @version 1.0
 * @see Rune
 */
public class PlayerRuneManager {
    private static PlayerRuneManager instance;

    private Rune playerRune;

    private Rune droppedRune;

    /**
     * Constructs a new PlayerRuneManager object.
     */
    private PlayerRuneManager(){
        playerRune = new Rune();
    };



    /**
     * Returns the instance of the PlayerRuneManager. If it doesn't exist yet, it creates a new instance and returns it.
     *
     * @return the instance of the PlayerRuneManager
     */
    public static PlayerRuneManager getInstance(){
        if (instance == null){
            instance = new PlayerRuneManager();
        }
        return instance;
    }

    /**
     * Increases the amount of this type of rune by the amount of another Rune object.
     *
     * @param rune The Rune object to add to this one.
     */
    public void increaseRune(Rune rune){
        playerRune.setAmount(rune.getAmount());
    }

    /**
     * Decreases the amount of this type of rune by the amount of another Rune object.
     *
     * @param rune The Rune object to subtract from this one.
     * @return True if the operation was successful (i.e. the amount of this type of rune is not negative after the operation), false otherwise.
     */
    public boolean decreaseRune(Rune rune){
        if (playerRune.getAmount() - rune.getAmount() >= 0){
            playerRune.setAmount(-rune.getAmount());
            return true;
        }else{
            return false;
        }
    }

    /**
     * Sets the dropped rune.
     *
     * @param droppedRune The Rune object that is dropped.
     */
    public void setDroppedRune(Rune droppedRune) {
        this.droppedRune = droppedRune;
    }

    /**
     * Returns the player's rune.
     *
     * @return the player's rune.
     */
    public Rune getPlayerRune() {
        return playerRune;
    }

    /**
     * Returns the dropped rune.
     *
     * @return the dropped rune.
     */
    public Rune getDroppedRune() {
        return droppedRune;
    }

    /**
     * Resets the player's rune to a new Rune object.
     */
    public void resetPlayerRune(){
        playerRune = new Rune();
    }

}
