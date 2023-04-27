package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AreaAttackAction;
import game.actions.AttackAction;
import game.runes.Rune;

import java.awt.geom.Area;

/**
 * A simple weapon that can be used to attack the enemy.
 * It deals 75 damage with 70% hit rate
 * Created by:
 * @author Aditti
 * Modified by:
 *
 */

public class Grossmesser extends WeaponItem implements Sellable {

    /**
     * Constructor.
     */
    public Grossmesser() {
        super("Grossmesser", '?', 115, "hit", 85);
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
    public Rune getSellingPrice() {
        return new Rune(100);
    }
}
