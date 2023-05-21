package game.gameactors.enemies.earth;


import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

/**
 * A powerful enemy of the player that can perform a powerful earthquake attack
 *
 * @author Tanul , Satoshi , Aditti
 * @version 1.0.0
 * @see EarthEnemy
 */
public class GlintstoneSorcerer extends EarthEnemy{
    /**
     * Constructor for the GlintstoneSorcerer abstract class.
     */
    public GlintstoneSorcerer() {
        super("Glintstone Sorcerer", 'e', 130, 121, 363);
    }

    /**
     * Returns the intrinsic weapon of the GlintstoneSorcerer.
     *
     * @return An IntrinsicWeapon object representing the Glintstone Sorcerer's intrinsic weapon.
     */

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(634, "devastates", 80);
    }
}
