package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.gameactors.FingerReaderEnia;
import game.gameactors.MerchantKale;
import game.items.Rune;


/**
 * The GraftedDragon class represents a weapon item called "Grafted Dragon" that extends the WeaponItem class.
 *
 * @author Tanul, Satoshi, Aditti
 * @version 1.0
 * @see WeaponItem
 * @see Sellable
 */
public class GraftedDragon extends WeaponItem implements Sellable{
    /**
     * Constructor.
     *
     */
    public GraftedDragon() {
        super("Grafted Dragon", 'N', 89, "bite", 90 );
        this.addCapability(WeaponSkill.TARGETED_ATTACK);
        this.addCapability(WeaponTradingAvailabilityStatus.SELLABLE);
    }

    @Override
    public Rune getSellingPrice() {
        return new Rune(200);
    }

    @Override
    public boolean isSellableToAnActor(Actor actor) {
        return actor instanceof FingerReaderEnia;
    }
}
