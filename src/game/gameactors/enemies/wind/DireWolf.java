package game.gameactors.enemies.wind;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;


/**
 * BEHOLD, DOG!
 * A type of enemy called DireWolf that extends the Enemy class.
 * It also has an intrinsic weapon bite that allows it to deal damage to its enemies.
 *
 * @author Tanul , Satoshi , Aditti
 * @version 1.0
 * @see WindEnemy
 */
public class DireWolf extends WindEnemy{


    /**
     * Constructor for the DireWolf class. Creates a new instance with the specific name, display character, hit points,
     * base damage, and base defense.
     */
    public DireWolf() {
        super("Direwolf", 'w',482 , 371, 3650);
    }


    /**
     * Retrieves the intrinsic weapon of the DireWolf, which is a biting attack which gives damage.
     *
     * @return the intrinsic weapon of the DireWolf.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(116, "bites", 85);
    }
}


