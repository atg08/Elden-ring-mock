package game.gameactors.enemies.fire;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

/**
 * A powerful enemy of the player that can perform a powerful burn attack
 *
 * @author Tanul , Satoshi , Aditti
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

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(397, "burns", 95);
    }
}
