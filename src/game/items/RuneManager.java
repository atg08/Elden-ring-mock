package game.items;

import game.reset.ResetManager;

/**
 * The RuneManager class manages the player's rune and provides operations to increase, decrease, and reset the rune.
 *
 * @version 1.0
 * @see Rune
 */
public class RuneManager {
    private static RuneManager instance;

    private Rune playerRune;

    private Rune droppedRune;

    private RuneManager(){
        playerRune = new Rune();
    };



    /**

     Returns the instance of this reset manager. If it doesn't exist yet, it creates a new instance and returns it.
     @return the instance of this reset manager
     */
    public static RuneManager getInstance(){
        if (instance == null){
            instance = new RuneManager();
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
     * @param droppedRune the Rune object that has been dropped
     */
    public void setDroppedRune(Rune droppedRune) {
        this.droppedRune = droppedRune;
    }

    /**
     * Retrieves the player's rune.
     *
     * @return the player's Rune object
     */
    public Rune getPlayerRune() {
        return playerRune;
    }

    /**
     * Retrieves the dropped rune.
     *
     * @return the dropped Rune object
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
