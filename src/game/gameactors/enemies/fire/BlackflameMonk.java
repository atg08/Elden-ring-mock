package game.gameactors.enemies.fire;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

/**
 * The BlackflameMonk class represents a type of FireEnemy called Blackflame Monk.
 * It extends the FireEnemy class.
 *
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

    /**
     * Retrieves the intrinsic weapon of the Blackflame Monk.
     *
     * @return an IntrinsicWeapon object representing the Blackflame Monk's intrinsic weapon
     * @see IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(488, "burns", 90);
    }
}
