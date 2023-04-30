package game.gameactors.enemies.graveyard;

import game.gameactors.enemies.Revivable;
import game.weapons.Scimitar;

/**
 * A Enemy of the player that holds a weapon called Grossmesser
 * @author tanul
 */

public class SkeletalBandit extends Skeleton {
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     * @author Tanul
     */
    public SkeletalBandit() {
        super("Skeletal Bandit", 'b', 184, 35, 892);
        this.addWeaponToInventory(new Scimitar());
    }

    @Override
    public Revivable revive() {
        return new SkeletalBandit();
    }

}
