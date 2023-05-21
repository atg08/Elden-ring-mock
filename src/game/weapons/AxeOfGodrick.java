package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.gameactors.FingerReaderEnia;
import game.gameactors.MerchantKale;
import game.items.Rune;

public class AxeOfGodrick extends WeaponItem implements Sellable{
    /**
     * Constructor.
     *
     */
    public AxeOfGodrick() {
        super("Axe of Godrick", 'T', 142, "chop", 84);
        this.addCapability(WeaponSkill.TARGETED_ATTACK);
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
