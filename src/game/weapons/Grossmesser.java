package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * A simple weapon that can be used to attack the enemy.
 * It deals 75 damage with 70% hit rate
 * Created by:
 * @author Aditti
 * Modified by:
 *
 */

public class Grossmesser extends WeaponItem {

    /**
     * Constructor.
     */
    public Grossmesser(String name, char displayChar, int damage, String verb, int hitRate) {
        super("Grossmesser", '?', 115, "hit", 85);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {}
}
