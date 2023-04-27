package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AreaAttackAction;
import game.actions.AttackAction;
import game.runes.Rune;

/**
 * A simple weapon that can be used to attack the enemy.
 * It deals 75 damage with 70% hit rate
 * Created by:
 * @author Aditti
 * Modified by:
 *
 */

public class Scimitar extends WeaponItem implements Purchasable, Sellable {

    /**
     * Constructor.
     */
    public Scimitar() {
        super("Scimitar", 's', 118, "hit", 88);
        this.addCapability(WeaponSpecialAbility.AREA_ATTACK);
    }

    @Override
    public Action getSkill(Actor target, String direction) {
        return new AttackAction(target, direction, this);
    }

    @Override
    public Action getSkill(Actor holder) {

        Action action = new AreaAttackAction(this);
        this.addAction(action);
        return action;
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {}

    @Override
    public Rune getPurchasingPrice() {
        return new Rune(600);
    }

    @Override
    public WeaponItem restock() {
        return new Scimitar();
    }

    @Override
    public Rune getSellingPrice() {
        return new Rune(100);
    }
}