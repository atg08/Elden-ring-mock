package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.QuickStepAction;
import game.items.Rune;

/**
 * A simple weapon that can be used to attack the enemy.
 * It deals 75 damage with 70% hit rate
 * Created by:
 * @author Aditti
 * Modified by:
 *
 */

public class GreatKnife extends WeaponItem implements Purchasable, Sellable {

    /**
     * Constructor.
     */
    public GreatKnife() {
        super("Great Knife", '/', 75, "hit", 70);
        this.addCapability(WeaponSkill.TARGETED_ATTACK);
        this.addCapability(WeaponTradingAvailabilityStatus.PURCHASABLE);
        this.addCapability(WeaponTradingAvailabilityStatus.SELLABLE);
    }

    @Override
    public Action getSkill(Actor target, String direction) {
        return new QuickStepAction(target, direction, this);
    }

    @Override
    public Rune getPurchasingPrice() {
        return new Rune(3500);
    }

    @Override
    public WeaponItem restock() {
        return new GreatKnife();
    }

    @Override
    public Rune getSellingPrice() {
        return new Rune(350);
    }

}
