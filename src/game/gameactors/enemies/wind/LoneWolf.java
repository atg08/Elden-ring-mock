package game.gameactors.enemies.wind;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.gameactors.enemies.Enemy;
import game.gameactors.players.Player;

/**
 * BEHOLD, DOG!
 *
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Tanul
 *
 */
public class LoneWolf extends Enemy {

    public LoneWolf() {
        super("Lone Wolf", 'h', 102, 55, 1470);
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(97, "bites", 95);
    }
}
