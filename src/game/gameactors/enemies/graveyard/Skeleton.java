package game.gameactors.enemies.graveyard;

import edu.monash.fit2099.engine.positions.GameMap;
import game.reset.Resettable;
import game.actions.DespawnAction;
import game.gameactors.EnemyType;
import game.gameactors.StatusActor;
import game.gameactors.enemies.DeathRuneDroppper;
import game.gameactors.enemies.Enemy;
import game.gameactors.enemies.Revivable;


/**
 * The Skeleton class represents a type of Enemy that is Revivable, Resettable, and able to drop Death Runes.
 *
 * @author Tanul , Satoshi , Aditti
 * @version 1.0.0
 */
 public abstract class Skeleton extends Enemy implements Revivable, Resettable, DeathRuneDroppper {
    /**
     * Constructor for the Skeleton class.
     *
     * @param name          the name of the Skeleton
     * @param displayChar   the character that will represent the Skeleton in the display
     * @param hitPoints     the starting hit points of the Skeleton
     * @param minRuneDrop   the minimum number of Death Runes that the Skeleton can drop upon death
     * @param maxRuneDrop   the maximum number of Death Runes that the Skeleton can drop upon death
     */
    public Skeleton(String name, char displayChar, int hitPoints, int minRuneDrop, int maxRuneDrop) {
        super(name, displayChar, hitPoints, minRuneDrop, maxRuneDrop);
        this.addCapability(EnemyType.SKELETON_TYPE);
        rm.registerResettable(this);
        this.addCapability(StatusActor.CAN_DESPAWN);
    }


    /**
     * Resets the Skeleton when it is killed by a player or another Actor.
     *
     * @param map the game map that the Skeleton is on
     * @return a message indicating that the Skeleton has been removed from the game map
     */
    // in skeleton for extensibility and modifications incase future enemies are not resettable
    @Override
    public String reset(GameMap map, boolean rest) {
        DespawnAction despawn = new DespawnAction();
        // this is so that enemy that has been removed
        // won't be attempted to be removed again
        return despawn.execute(this, map);
    }

    /**
     * Checks if the Skeleton can be removed from the map.
     *
     * @return true since the Skeleton can be removed from the map
     */
    @Override
    public boolean isRemovable() {
        return true;
    }

    public boolean isRemovableOnPlayerRest() {
        return true;
    }

}
