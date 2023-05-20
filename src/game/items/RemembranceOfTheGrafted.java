package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.weapons.AxeOfGodrick;
import game.weapons.GraftedDragon;

import java.util.List;


/**
 * The {@code RemembranceOfTheGrafted} class represents a special item called "Remembrance of the Grafted."
 * It is a subclass of the {@code Item} class and implements the {@code Exchangeable} interface.
 * It contains a list of available weapons for exchange.
 *
 * @version 1.0
 * @see Item
 * @see Exchangeable
 */
public class RemembranceOfTheGrafted extends Item implements Exchangeable{
    List<WeaponItem> availableWeaponsForExchange;

    /**
     * Constructs a new {@code RemembranceOfTheGrafted} object.
     * Initializes the item name, symbol, and sets it as non-disposable.
     * Also adds the capability of being exchangeable.
     */
    public RemembranceOfTheGrafted() {
        super("Remembrance of the Grafted", 'O', false);
        this.addCapability(ItemUsage.EXCHANGEABLE);
        this.availableWeaponsForExchange.add(new AxeOfGodrick());
        this.availableWeaponsForExchange.add(new GraftedDragon());
    }


    /**
     * Returns the list of available weapons for exchange.
     *
     * @return the list of available weapons for exchange
     */
    @Override
    public List<WeaponItem> getAvailableWeaponsForExchange() {
        return this.availableWeaponsForExchange;
    }
}
