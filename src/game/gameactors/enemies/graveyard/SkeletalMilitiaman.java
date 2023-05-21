package game.gameactors.enemies.graveyard;

import game.gameactors.enemies.Revivable;
import game.weapons.Scimitar;


/**
 * A type of enemy that holds a weapon called Scimitar.
 *
 * @author Tanul , Satoshi , Aditti
 * @version 1.0.0
 * @see Skeleton
 * @see Revivable
 */
public class SkeletalMilitiaman extends Skeleton implements Revivable {

    /**
     * Constructor for the SkeletalMilitiaman class.
     */
    public SkeletalMilitiaman() {
        super("Skeletal Militiaman", 'm', 207, 35, 682);
        this.addWeaponToInventory(new Scimitar());
    }


    /**
     * Returns a new instance of the SkeletalMilitiaman class to revive this enemy.
     *
     * @return a new instance of the SkeletalMilitiaman class
     */
    @Override
    public Revivable revive() {
        return new SkeletalMilitiaman();
    }
}
