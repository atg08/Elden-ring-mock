package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.items.Rune;

/**
 * A simple weapon that can be used to attack the enemy.
 * It deals 103 damage with 80% hit rate
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Aditti
 * @see WeaponItem
 * @see Purchasable
 * @see Sellable
 *
 */

public class Club extends WeaponItem implements Purchasable, Sellable {

    /**
     * Constructor
     */
    public Club() {
        super("Club", '!', 103, "bonks", 80);
        this.addCapability(WeaponSkill.TARGETED_ATTACK);
        this.addCapability(WeaponTradingAvailabilityStatus.PURCHASABLE);
        this.addCapability(WeaponTradingAvailabilityStatus.SELLABLE);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {}

    /**
     * Returns the purchasing price of the Club.
     *
     * @return the purchasing price of the Club as a Rune object
     */
    @Override
    public Rune getPurchasingPrice() {
        return new Rune(600);
    }

    /**
     * Restocks the Club weapon item.
     *
     * @return a new Club object as a restocked item
     */
    @Override
    public WeaponItem restock() {
        return new Club();
    }

    /**
     * Returns the selling price of the Club.
     *
     * @return the selling price of the Club as a Rune object
     */
    @Override
    public Rune getSellingPrice() {
        return new Rune(100);
    }
}


