package game.gameactors.enemies.fire;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

public class FireMonk extends FireEnemy{
    /**
     * Constructor for the Enemy class.
     */
    public FireMonk() {
        super("Fire Monk", 'r', 952, 205, 1076);
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(397, "burns", 95);
    }
}
