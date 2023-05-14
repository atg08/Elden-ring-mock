package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.weapons.AxeOfGodrick;
import game.weapons.GraftedDragon;

import java.util.List;

public class RemembranceOfTheGrafted extends Item implements Exchangeable{
    List<WeaponItem> availableWeaponsForExchange;

    /***
     * Constructor.
     */
    public RemembranceOfTheGrafted() {
        super("Remembrance of the Grafted", 'O', false);
        this.addCapability(ItemUsage.EXCHANGEABLE);
        this.availableWeaponsForExchange.add(new AxeOfGodrick());
        this.availableWeaponsForExchange.add(new GraftedDragon());
    }

    @Override
    public List<WeaponItem> getAvailableWeaponsForExchange() {
        return this.availableWeaponsForExchange;
    }
}
