package game.gameactors.enemies.graveyard;

import game.gameactors.enemies.Revivable;
import game.weapons.Grossmesser;

/**
 * A type of enemy that holds a weapon called Grossmesser.
 *
 * @author Tanul , Satoshi , Aditti
 * @version 1.0.0
 */

public class HeavySkeletonSwordsman extends Skeleton implements Revivable {

    /**
     * Constructor for the HeavySkeletonSwordsman class.
     */
    public HeavySkeletonSwordsman() {
        super("Heavy Skeleton Swordsman", 'q', 153, 35, 892);
        this.addWeaponToInventory(new Grossmesser());
    }


    /**
     * Returns a new instance of the HeavySkeletonSwordsman class to revive this enemy.
     *
     * @return a new instance of the HeavySkeletonSwordsman class
     */
    @Override
    public Revivable revive(){
        return new HeavySkeletonSwordsman();
    }

}
