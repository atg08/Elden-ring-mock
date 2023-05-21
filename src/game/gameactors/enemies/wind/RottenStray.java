package game.gameactors.enemies.wind;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;


/**
 * This class represents a type of WindEnemy called RottenStray, which has a specific set of stats and capabilities.
 * It extends the WindEnemy class and overrides the getIntrinsicWeapon method to return a specific weapon.
 *
 * @author Tanul , Satoshi , Aditti
 * @version 1.0.0
 * @see WindEnemy
 */
public class RottenStray extends WindEnemy{


    /**
     * Constructor for RottenStray.
     */
    public RottenStray() {
        super("Rotten Stray", 't', 213, 52, 1892);
    }


    /**
     * Returns the intrinsic weapon of the RottenStray.
     *
     * @return the intrinsic weapon of the RottenStray, which is an instance of the IntrinsicWeapon class.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(248, "bashes", 80);
    }
}
