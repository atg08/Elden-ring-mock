package game.gameactors.enemies.graveyard;

import game.gameactors.enemies.Revivable;
import game.gameactors.players.Player;
import game.weapons.Scimitar;

/**
 * A Enemy of the player that holds a weapon called Grossmesser
 *
 * @author Tanul , Satoshi , Aditti
 * @version 1.0.0
 * @see Skeleton
 * @see Revivable
 */

public class SkeletalBandit extends Skeleton implements Revivable{
    /**
     * Constructor for creating a new SkeletalBandit object.
     * Sets the enemy's name, character symbol, health points, damage, and defense.
     * Adds a Scimitar weapon to the enemy's inventory.
     */
    public SkeletalBandit() {
        super("Skeletal Bandit", 'b', 184, 35, 892);
        this.addWeaponToInventory(new Scimitar());
    }


    /**
     * Revives a dead SkeletalBandit.
     * @return a new SkeletalBandit object
     */
    @Override
    public Revivable revive() {
        return new SkeletalBandit();
    }

}
