package game.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.items.Rune;

/**
 * The Purchasable interface represents an item that can be purchased.
 * It defines methods for retrieving the purchasing price and restocking the item.
 *
 * @version 1.0
 * @see Rune
 * @see WeaponItem
 */

public interface Purchasable {

    /**
     * Retrieves the purchasing price of the item.
     *
     * @return The purchasing price of the item as a Rune object.
     */
    public Rune getPurchasingPrice();

    /**
     * Restocks the item.
     *
     * @return A new instance of the restocked WeaponItem.
     */
    public WeaponItem restock();
}
