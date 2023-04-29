package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AreaAttackAction;
import game.runes.Rune;

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
        this.addCapability(WeaponSkill.AREA_ATTACK);
        this.addCapability(WeaponSkill.TARGETED_ATTACK);
    }

    @Override
    public Rune getSellingPrice() {
        return new Rune(100);
    }
}
