package game.gameactors.enemies.graveyard;

import game.gameactors.enemies.Revivable;
import game.weapons.Grossmesser;

/**
 * A Enemy of the player that holds a weapon called Grossmesser
 * @author tanul
 */
public class HeavySkeletonSwordsman extends Skeleton {

    /**
     * Constructor.
     */
    public HeavySkeletonSwordsman() {
        super("Heavy Skeleton Swordsman", 'q', 153, 35, 892);
        this.addWeaponToInventory(new Grossmesser());
    }

    @Override
    public Revivable revive(){
        return new HeavySkeletonSwordsman();
    }
}
