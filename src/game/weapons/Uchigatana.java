package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.UnsheatheAction;
import game.items.Rune;

/**
 * A simple weapon that can be used to attack the enemy.
 * It deals 75 damage with 70% hit rate
 * Created by:
 * @author Aditti
 * @version 1.0
 * @see WeaponItem
 * @see Purchasable
 * @see Sellable
 *
 */

public class Uchigatana extends WeaponItem implements Purchasable, Sellable {

    /**
     * Constructor.
     */
    public Uchigatana() {

        super("Uchigatana", ')', 115, "hit", 80);
        this.addCapability(WeaponSkill.TARGETED_ATTACK);
        this.addCapability(WeaponTradingAvailabilityStatus.PURCHASABLE);
        this.addCapability(WeaponTradingAvailabilityStatus.SELLABLE);

    }

    /**
     * Retrieves the skill action associated with the Uchigatana.
     *
     * @param target    The target actor for the skill action.
     * @param direction The direction of the skill action.
     * @return The UnsheatheAction representing the skill action.
     */
    public Action getSkill(Actor target, String direction) {
        return new UnsheatheAction(target, direction, this);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {}

    /**
     * {@inheritDoc}
     *
     * @return The purchasing price of the Uchigatana as a Rune.
     */
    @Override
    public Rune getPurchasingPrice() {
        return new Rune(5000);
    }

    /**
     * {@inheritDoc}
     *
     * @return A new instance of the Uchigatana to restock the item.
     */
    @Override
    public WeaponItem restock() {
        return new Uchigatana();
    }

    /**
     * {@inheritDoc}
     *
     * @return The selling price of the Uchigatana as a Rune.
     */
    @Override
    public Rune getSellingPrice() {
        return new Rune(500);
    }
}
