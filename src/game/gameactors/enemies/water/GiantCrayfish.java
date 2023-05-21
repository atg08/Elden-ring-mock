package game.gameactors.enemies.water;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;


/**
 * A powerful enemy of the player that can perform a powerful slam attack
 *
 * @author Tanul , Satoshi , Aditti
 * @version 1.0
 * @see OceanEnemy
 */

public class GiantCrayfish extends OceanEnemy{

    /**
     * Default constructor for the GiantCrayfish class.
     * It initializes the GiantCrayfish with default values for its name, display character, health points,
     * attack points, and defense points.
     */
    public GiantCrayfish() {
        super("Giant Crayfish", 'R', 4803, 500, 2374);
    }


    /**
     * Returns the intrinsic weapon of the GiantCrayfish.
     *
     * @return An IntrinsicWeapon object representing the giant crayfish's intrinsic weapon.
     */
    // giant Pincer
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(527, "slams", 100);
    }
}
