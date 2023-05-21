package game.items;

import edu.monash.fit2099.engine.weapons.WeaponItem;

import java.util.List;


/**
 * The Exchangeable interface represents an entity that can provide a list of available weapons for exchange.
 *
 * @author Tanul, Satoshi, Aditti
 * @version 1.0
 * @see WeaponItem
 */
public interface Exchangeable {

    /**
     * Retrieves a list of available weapons for exchange.
     *
     * @return the list of available weapons for exchange
     */
    List<WeaponItem> getAvailableWeaponsForExchange();
}
