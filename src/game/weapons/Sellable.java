package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import game.items.Rune;


/**
 * The Sellable interface represents an item that can be sold.
 * It defines a method for retrieving the selling price of the item.
 *
 * @version 1.0
 * @see Rune
 */

public interface Sellable {
    /**
     * Retrieves the selling price of the item.
     *
     * @return the selling price of the item
     */
    Rune getSellingPrice();
    boolean isSellableToAnActor(Actor actor);
}
