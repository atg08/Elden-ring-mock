package game.gameactors.enemies.wind;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.gameactors.enemies.Enemy;
import game.gameactors.players.Player;

/**
 * BEHOLD, DOG!
 *
 * A type of enemy called Lone Wolf that extends the Enemy class.
 * It also has an intrinsic weapon bite that allows it to deal damage to its enemies.
 *
 * @author Adrian Kristanto
 * @modifiedBy Tanul , Satoshi , Aditti
 * @version 1.0
 * @see WindEnemy
 */
public class LoneWolf extends WindEnemy {


    /**
     * Constructor for the LoneWolf class. Creates a new instance with the specific name, display character, hit points,
     * base damage, and base defense.
     */
    public LoneWolf() {
        super("Lone Wolf", 'h', 102, 55, 1470);
    }


    /**
     * Retrieves the intrinsic weapon of the LoneWolf, which is a biting attack which gives damage.
     *
     * @return the intrinsic weapon of the LoneWolf.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(97, "bites", 95);
    }
}
