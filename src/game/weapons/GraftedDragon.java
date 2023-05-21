package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.gameactors.FingerReaderEnia;
import game.gameactors.MerchantKale;
import game.items.Rune;

public class GraftedDragon extends WeaponItem implements Sellable{
    /**
     * Constructor.
     *
     */
    public GraftedDragon() {
        super("Grafted Dragon", 'N', 89, "bite", 90 );
        this.addCapability(WeaponSkill.TARGETED_ATTACK);
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
