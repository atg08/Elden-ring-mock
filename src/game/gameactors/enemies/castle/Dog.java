package game.gameactors.enemies.castle;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

/**
 * The Dog class represents a dog enemy in a castle.
 * It extends the CastleEnemy class.
 *
 * @author Aditti
 * @version 1.0
 * @see CastleEnemy
 */
public class Dog extends CastleEnemy {
    /**
     * Constructor for the Dog class.
     */
    public Dog() {
        super("Dog", 'a', 104, 52, 1390);
    }

    /**
     * Retrieves the intrinsic weapon of the dog.
     *
     * @return The intrinsic weapon of the dog.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(101, "bites", 93);
    }

}
