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

public class GiantCrab extends OceanEnemy{


    /**
     * Constructor for the GiantCrab class.
     */
    public GiantCrab() {
        super("Giant Crab", 'C', 208, 318, 4961);
    }



    /**
     * Returns an IntrinsicWeapon object representing the GiantCrab's slam attack.
     *
     * @return An IntrinsicWeapon object representing the GiantCrab's slam attack.
     */

    // slam cna be individual or area attack
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(208, "slams", 90);
    }

}
