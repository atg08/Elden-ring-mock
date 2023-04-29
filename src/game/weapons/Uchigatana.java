package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.UnsheatheAction;
import game.runes.Rune;

/**
 * A simple weapon that can be used to attack the enemy.
 * It deals 75 damage with 70% hit rate
 * Created by:
 * @author Aditti
 * Modified by:
 *
 */

public class Uchigatana extends WeaponItem implements Purchasable, Sellable {

    /**
     * Constructor.
     */
    public Uchigatana() {
        super("Uchigatana", ')', 115, "hit", 80);
        this.addCapability(WeaponSkill.TARGETED_ATTACK);
    }

    @Override
    public Action getSkill(Actor target, String direction) {
        return new UnsheatheAction();
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {}

    @Override
    public Rune getPurchasingPrice() {
        return new Rune(5000);
    }

    @Override
    public WeaponItem restock() {
        return new Uchigatana();
    }

    @Override
    public Rune getSellingPrice() {
        return new Rune(500);
    }
}
