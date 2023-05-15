package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.reset.ResetManager;
import game.reset.Resettable;
import game.actions.HealAction;


/**

 An Item that can be consumed to heal the player's hit points

 Has a limited number of uses before becoming unusable

 Implements the Resettable interface to allow for resetting the item's usage count

 @see Resettable

 @see game.reset.ResetManager

 @see HealAction

 @author tanul
 */

public class FlaskOfCrimsonTears extends Item implements Resettable, Healer {

    /**

     The amount of hit points this item heals
     */
    private final int HEAL_AMOUNT = 250;
    /**

     The maximum number of times this item can be consumed
     */
    private final int MAX_CONSUME_AMOUNT = 2;

    /**

     The number of times this item has been consumed
     */
    private int consumedCount;

    /**

     The ResetManager instance used to register this item as Resettable
     */
    private ResetManager rm = ResetManager.getInstance();

    /**

     Constructor.
     Initializes the item's name, display character, and other properties
     Registers this item as Resettable with the ResetManager
     Adds the ConsumeAction to the item's available actions
     */
    public FlaskOfCrimsonTears() {
        //not portable so cannot be piccked up and dropped
        super("Flask Of Crimson Tears",'E', false);
        this.addCapability(ItemUsage.CAN_CONSUME_TO_HEAL);
        this.consumedCount = 0;
        rm.registerResettable(this);
        // upon initialization of the object adds the object
        // to the list of resettable

        this.addAction(new HealAction(this));
    }


    /**

     Returns the amount of hit points this item heals
     @return The amount of hit points this item heals
     */
    public int getHEAL_AMOUNT() {
        return HEAL_AMOUNT;
    }


    /**

     Resets the count of times this item has been consumed to 0
     */
    private void resetConsumed() {
        this.consumedCount = 0;
    }

    /**

     Returns the name of the item and the number of times it can still be consumed
     @return The name of the item and the number of times it can still be consumed
     */
    @Override
    public String toString() {
        return "Flask Of Crimson Tears (" + (this.MAX_CONSUME_AMOUNT-this.consumedCount) + "/" + this.MAX_CONSUME_AMOUNT + ")";
    }

    /**

     Resets the count of times this item has been consumed to 0 and returns a string indicating the item has been reset
     @param actor The actor resetting the item
     @param map The GameMap on which the reset is taking place
     @return A string indicating the item has been reset
     */
    @Override
    public String reset(Actor actor, GameMap map) {
        this.resetConsumed();
        return this + "has been reset";
    }

    /**

     Indicates whether this item can be removed from an inventory or not
     @return False, as this item cannot be removed from an inventory
     **/
    @Override
    public boolean isRemovable() {
        return false;
    }

    public boolean isRemovableOnPlayerRest() {
        return false;
    }

    @Override
    public boolean isAvailable() {
        return this.MAX_CONSUME_AMOUNT - this.consumedCount > 0;
    }


    @Override
    public int getHealAmount() {
        return this.HEAL_AMOUNT;
    }

    /**

     Increases the count of times this item has been consumed by 1
     */
    @Override
    public void updateStatus() {
        this.consumedCount += 1;
    }
}
