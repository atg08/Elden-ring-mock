package game.gameactors.enemies.castle;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

public class Dog extends CastleEnemy {
    /**
     * Constructor for the Enemy class.
     */
    public Dog() {
        super("Dog", 'a', 104, 52, 1390);
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(101, "bites", 93);
    }

}
