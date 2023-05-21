package game.gameactors.enemies.fire;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.gameactors.enemies.earth.EarthEnemy;

/**
 * A powerful enemy of the player that can perform a powerful burn attack
 *
 * @author Tanul , Satoshi , Aditti
 * @version 1.0
 * @see FireEnemy
 */
public class BlackflameMonk extends FireEnemy{
    /**
     * Constructor for the Enemy class.
     */
    public BlackflameMonk() {
        super("Blackflame Monk", 'l', 683, 830, 1764);
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(488, "burns", 90);
    }
}
