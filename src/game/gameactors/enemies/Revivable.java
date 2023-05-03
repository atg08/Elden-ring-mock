package game.gameactors.enemies;


/**
 * The Revivable interface represents an enemy that can be revived, the HeavySkeletonSwordsman and the SkeletalBandit
 * It provides a method to revive the enemy.
 *
 * @author Tanul , Satoshi , Aditti
 * @version 1.0.0
 */
public interface Revivable {

    /**
     * Revives the enemy.
     *
     * @return a new instance of the revived enemy.
     */
    public Revivable revive();
}
