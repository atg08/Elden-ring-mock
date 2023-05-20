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
 * @see WeaponItem
 * @see Purchasable
 * @see Sellable
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

    /**
     * Retrieves the skill associated with the GreatKnife weapon.
     *
     * @param target    the actor to perform the skill on
     * @param direction the direction in which the skill is performed
     * @return an Action object representing the skill
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        return new QuickStepAction(target, direction, this);
    }

    /**
     * Retrieves the purchasing price of the GreatKnife weapon.
     *
     * @return a Rune object representing the purchasing price
     */
    @Override
    public Rune getPurchasingPrice() {
        return new Rune(3500);
    }

    /**
     * Restocks the GreatKnife weapon item.
     *
     * @return a new instance of the GreatKnife weapon item
     */
    @Override
    public WeaponItem restock() {
        return new GreatKnife();
    }

    /**
     * Retrieves the selling price of the GreatKnife weapon.
     *
     * @return a Rune object representing the selling price
     */
    @Override
    public Rune getSellingPrice() {
        return new Rune(350);
    }

}
