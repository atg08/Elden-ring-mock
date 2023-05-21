package game.gameactors.enemies.water;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;


/**
 * An enemy of the player that can perform a powerful slam attack.
 * Extends the OceanEnemy class.
 *
 * @author Tanul , Satoshi , Aditti
 * @version 1.0
 * @see OceanEnemy
 */

public class GiantDragonfly extends OceanEnemy{

    /**
     * Default constructor for the GiantDragonfly class.
     * It initializes the GiantDragonfly with default values for its name, display character, health points,
     * attack points, and defense points.
     */
    public GiantDragonfly() {
        super("Giant Dragonfly", 'F', 17, 4, 41);
    }


    /**
     * Returns the intrinsic weapon of the GiantDragonfly
     *
     * @return An IntrinsicWeapon object representing the giant dragonfly's intrinsic weapon.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(64, "slams", 20);
    }
}
