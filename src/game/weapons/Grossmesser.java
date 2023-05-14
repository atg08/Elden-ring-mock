package game.weapons;


import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.items.Rune;

/**
 * A simple weapon that can be used to attack the enemy.
 *
 * @author Tanul , Satoshi , Aditti
 * @version 1.0.0
 */

public class Grossmesser extends WeaponItem implements Sellable {

    /**
     * Creates a Grossmesser weapon instance.
     */
    public Grossmesser() {
        super("Grossmesser", '?', 115, "hit", 85);
        this.addCapability(WeaponSkill.AREA_ATTACK);
        this.addCapability(WeaponSkill.TARGETED_ATTACK);
        this.addCapability(WeaponTradingAvailabilityStatus.SELLABLE);
    }


    /**
     * Creates Rune with the selling price of the Grossmesser weapon.
     *
     * @return A Rune object representing the selling price of the Grossmesser weapon.
     */
    @Override
    public Rune getSellingPrice() {
        return new Rune(100);
    }
}
