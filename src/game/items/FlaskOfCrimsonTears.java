package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ConsumeAction;

import java.util.List;


/**
 * An Item that can be consumed to heal the player's hit points
 * @author tanul
 */

public class FlaskOfCrimsonTears extends Item {

    /**
     * AMOUNT THE ITEM CAN HEAL
     */
    private final int HEAL_AMOUNT = 250;
    /**
     * AMOUNT OF TIMES THE ITEM CAN BE USED
     */
    private final int MAX_CONSUME_AMOUNT = 2;

    /**
     * AMOUNT THE ITEM HAS BEEN USED
     */
    private int consumed;

    /***
     * Constructor.
     * no use of display char at this stage as this item shouldn't be
     * able to be dropped
     */
    public FlaskOfCrimsonTears() {
        //not portable so cannot be piccked up and dropped
        super("Flask Of Crimson Tears",'E', false);
        this.addCapability(ItemUsage.CAN_CONSUME_TO_HEAL);
        this.addCapability(ItemUsage.IS_FLASK);
        this.consumed = 0;
    }

    /**
     * getter method that RETURNS THE NUMBER OF TIMES THE ITEM HAS BEEN USED
     * @return THE NUMBER OF TIMES THE ITEM HAS BEEN USED
     */
    public int getConsumed() {
        return consumed;
    }

    /**
     * getter method that RETURNS THE MAXIMUM NUMBER OF TIMES THE ITEM CAN BE USED
     * @return MAX_CONSUME_AMOUNT
     */
    public int getMAX_CONSUME_AMOUNT() {
        return MAX_CONSUME_AMOUNT;
    }

    /**
     * getter method that RETURNS THE AMOUNT THE ITEM HEALS
     * @return HEAL_AMOUNT
     */
    public int getHEAL_AMOUNT() {
        return HEAL_AMOUNT;
    }

    /**
     * method TO UPDATE THE NUMBER OF TIMES ITEM HAS BEEN CONSUMED
     */
    public void updateConsumed() {
        this.consumed += 1;
    }

    /**
     *
     * @return THE NAME OF THE ITEM
     */
    @Override
    public String toString() {
        return "FlaskOfCrimsonTears";
    }

}
