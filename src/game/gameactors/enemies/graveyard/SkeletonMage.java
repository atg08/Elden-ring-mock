package game.gameactors.enemies.graveyard;

import game.gameactors.enemies.Revivable;
import game.weapons.Grossmesser;


/**
 * A type of enemy that holds a weapon called Grossmesser.
 *
 * @author Tanul , Satoshi , Aditti
 * @version 1.0.0
 */
public class SkeletonMage extends Skeleton implements Revivable {

    /**
     * Constructor for the SkeletonMage class.
     */
    public SkeletonMage() {
        super("Skeleton mage", 'M', 235, 108, 682);
        this.addWeaponToInventory(new Grossmesser());
    }


    /**
     * Returns a new instance of the SkeletonMage class to revive this enemy.
     *
     * @return a new instance of the SkeletonMage class
     */
    @Override
    public Revivable revive() {
        return new SkeletonMage();
    }
}
