package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.RecoverRuneAction;
import game.gameactors.StatusActor;
import game.gameactors.players.Player;
import game.reset.ResetManager;
import game.reset.Resettable;

/**
 * A class representing a Rune item in the game, which can be picked up and used as a form of currency.
 * Implements the Resettable interface for map resetting functionality.
 * @author Satoshi Kashima
 */
public class Rune extends Item implements Resettable{

    /** The amount of this type of rune. */
    private int amount = 0;

    /** The location of the rune. */
    private Location runeLocation = null;

    /** The ResetManager instance. */
    private ResetManager rm = ResetManager.getInstance();

    /** The maximum number of reset attempts before the rune is removed from the map. */
    private final int MAX_RESET_COUNT_FOR_REMOVAL = 2;

    /** The number of reset attempts made so far. */
    private int reset_attempts = 0;

    /**
     * Constructor for a Rune object with a default amount of 0.
     */
    public Rune() {
        super("rune", '$', false);
    }

    /**
     * Constructor for a Rune object with a specified amount.
     *
     * @param amount The initial amount of this type of rune.
     */
    public Rune(int amount){
        super("rune", '$', false);
        this.amount = amount;

    }

    public void setAmount(int amount) {
        this.amount += amount;
    }

    /**
     * Increases the amount of this type of rune by the amount of another Rune object.
     *
     * @param rune The Rune object to add to this one.
     */
    public void increaseRune(Rune rune){
        this.amount += rune.getAmount();
    }

    /**
     * Returns the amount of this type of rune.
     *
     * @return The amount of this type of rune.
     */
    public int getAmount() {
        return this.amount;
    }

    /**
     * Decreases the amount of this type of rune by the amount of another Rune object.
     *
     * @param rune The Rune object to subtract from this one.
     * @return True if the operation was successful (i.e. the amount of this type of rune is not negative after the operation), false otherwise.
     */
    public boolean decreaseRune(Rune rune){
        if (this.amount - rune.getAmount() >= 0){
            this.amount -= rune.getAmount();
            return true;
        }else{
            return false;
        }
    }

    /**
     * Sets the location of the rune.
     *
     * @param runeLocation The location of the rune.
     */
    public void setRuneLocation(Location runeLocation) {
        this.runeLocation = runeLocation;
    }

    /**
     * Registers this object as a Resettable object with the ResetManager.
     */
    public void registerAsResettable(){
        this.rm.registerResettable(this);
    }

    /**
     * Resets the state of the object, removing it from the map if it has been reset too many times.
     *
     * @param map The game map.
     * @return A string describing the result of the reset operation.
     */
    @Override
    public String reset(GameMap map, boolean rest) {
        if (!rest && this.checkForRemoval()){
            runeLocation.removeItem(this);
            return "rune removed from the map";
        }
        this.rm.registerAsResettableAgain(this);
        return "";
    }

    /**
     * Checks for removal of this rune from the map.
     * @return true if need to be removed
     */
    public boolean checkForRemoval(){
        this.reset_attempts += 1;
        return this.reset_attempts == this.MAX_RESET_COUNT_FOR_REMOVAL;
    }

    @Override
    public boolean isRemovable() {
        return true;
    }

    @Override
    public boolean isRemovableOnPlayerRest() {
        return false;
    }


    @Override
    public PickUpAction getPickUpAction(Actor actor) {
        return new RecoverRuneAction(this);
    }
}
