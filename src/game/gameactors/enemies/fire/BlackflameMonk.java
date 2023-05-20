package game.gameactors.enemies.fire;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

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
