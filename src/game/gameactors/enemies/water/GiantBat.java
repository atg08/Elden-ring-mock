package game.gameactors.enemies.water;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;


/**
 * An enemy of the player that can perform a powerful slam attack.
 * Extends the OceanEnemy class.
 *
 * @author Tanul , Satoshi , Aditti
 * @version 1.0.0
 */
public class GiantBat extends OceanEnemy{

    /**
     * Default constructor for the GiantBat class.
     * It initializes the GiantBat with default values for its name, display character, health points,
     * attack points, and defense points.
     */
    public GiantBat() {
        super("Giant Bat", 'T', 161, 47, 887);
    }


    /**
     * Returns the intrinsic weapon of the GiantBat
     *
     * @return An IntrinsicWeapon object representing the giant bat's intrinsic weapon.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {return new IntrinsicWeapon(257, "slams", 60);
    }
}
