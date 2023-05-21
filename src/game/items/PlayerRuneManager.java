package game.items;

public class PlayerRuneManager {
    private static PlayerRuneManager instance;

    private Rune playerRune;

    private Rune droppedRune;

    private PlayerRuneManager(){
        playerRune = new Rune();
    };



    /**

     Returns the instance of this reset manager. If it doesn't exist yet, it creates a new instance and returns it.
     @return the instance of this reset manager
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

    public void setDroppedRune(Rune droppedRune) {
        this.droppedRune = droppedRune;
    }

    public Rune getPlayerRune() {
        return playerRune;
    }

    public Rune getDroppedRune() {
        return droppedRune;
    }

    public void resetPlayerRune(){
        playerRune = new Rune();
    }

}
