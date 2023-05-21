package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.gameactors.FingerReaderEnia;
import game.gameactors.MerchantKale;
import game.items.Rune;


/**
 * The AxeOfGodrick class represents an axe item called "Axe of Godrick" that extends the WeaponItem class.
 *
 * @author Tanul, Satoshi, Aditti
 * @version 1.0
 * @see WeaponItem
 * @see Sellable
 */
public class AxeOfGodrick extends WeaponItem implements Sellable{

    /**
     * Constructs a new AxeOfGodrick object.
     * The axe has the name "Axe of Godrick" and a symbol 'T'.
     * It has 142 damage points and has a damage type of "chop" with a damage value of 84.
     * It is capable of performing targeted attacks.
     */
    public AxeOfGodrick() {
        super("Axe of Godrick", 'T', 142, "chop", 84);
        this.addCapability(WeaponSkill.TARGETED_ATTACK);
        this.addCapability(WeaponTradingAvailabilityStatus.SELLABLE);
    }

    @Override
    public Rune getSellingPrice() {
        return new Rune(100);
    }

    @Override
    public boolean isSellableToAnActor(Actor actor) {
        return actor instanceof FingerReaderEnia;
    }
}
