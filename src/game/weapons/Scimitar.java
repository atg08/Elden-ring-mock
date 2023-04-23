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

public class Scimitar extends WeaponItem {

    /**
     * Constructor.
     */
    public Scimitar(String name, char displayChar, int damage, String verb, int hitRate) {
        super("Scimitar", 's', 118, verb, 88);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {}
}
