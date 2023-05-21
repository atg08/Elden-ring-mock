package game.gameactors.enemies.fire;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

/**
 * The FireMonk class represents a type of enemy called Fire Monk, which is a subclass of FireEnemy.
 * It has specific characteristics and abilities related to fire.
 *
 * @version 1.0
 * @see FireEnemy
 */
public class FireMonk extends FireEnemy{
    /**
     * Constructor for the Enemy class.
     */
    public FireMonk() {
        super("Fire Monk", 'r', 952, 205, 1076);
    }

    /**
     * Returns the intrinsic weapon of the FireMonk.
     *
     * @return the intrinsic weapon object representing the FireMonk's attack.
     * @see IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(397, "burns", 95);
    }
}
