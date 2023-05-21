package game.gameactors.enemies.earth;


import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

/**
 * A powerful enemy of the player that can perform a powerful earthquake attack
 *
 * @author Tanul , Satoshi , Aditti
 * @version 1.0.0
 * @see EarthEnemy
 */
public class TrollKnight extends EarthEnemy {
    /**
     * Constructor for the TrollKnight abstract class.
     */
    public TrollKnight() {
        super("Troll Knight", 'O', 622, 1320, 1342);
    }

    /**
     * Returns the intrinsic weapon of the TrollKnight.
     *
     * @return An IntrinsicWeapon object representing the TrollKnight's intrinsic weapon.
     */

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(725, "devastates", 100);
    }
}
