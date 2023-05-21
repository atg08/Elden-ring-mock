package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.gameactors.MerchantKale;
import game.items.Rune;


/**
 * A simple weapon that can be used to attack the enemy.
 * It deals 75 damage with 70% hit rate
 * Created by:
 * @author Aditti
 * @see WeaponItem
 * @see Purchasable
 * @see Sellable
 * @version 1.0
 *
 */

public class Scimitar extends WeaponItem implements Purchasable, Sellable {

    /**
     * Constructor.
     */
    public Scimitar() {
        super("Scimitar", 's', 118, "hit", 88);
        this.addCapability(WeaponSkill.AREA_ATTACK);
        this.addCapability(WeaponTradingAvailabilityStatus.PURCHASABLE);
        this.addCapability(WeaponTradingAvailabilityStatus.SELLABLE);
    }

    /**
     * Retrieves the purchasing price of the Scimitar.
     *
     * @return the Rune object representing the purchasing price
     * @see Rune
     */
    @Override
    public Rune getPurchasingPrice() {
        return new Rune(600);
    }


    /**
     * Restocks the Scimitar weapon item.
     *
     * @return a new Scimitar object
     * @see Scimitar
     */
    @Override
    public WeaponItem restock() {
        return new Scimitar();
    }


    /**
     * Retrieves the selling price of the Scimitar.
     *
     * @return the Rune object representing the selling price
     * @see Rune
     */
    @Override
    public Rune getSellingPrice() {
        return new Rune(100);
    }

    @Override
    public boolean isSellableToAnActor(Actor actor) {
        return actor instanceof MerchantKale;
    }
}
