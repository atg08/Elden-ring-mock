package game.gameactors.enemies.castle;

import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.DespawnAction;
import game.gameactors.EnemyType;
import game.gameactors.StatusActor;
import game.gameactors.enemies.Enemy;
import game.reset.Resettable;

/**
 * The CastleEnemy class is an abstract class that extends the Enemy class and implements the Resettable interface.
 * It represents enemies in a castle in a game.
 *
 * @author Aditti
 * @see Enemy
 * @see Resettable
 * @version 1.0
 */
public abstract class CastleEnemy extends Enemy implements Resettable {
    /**
     * Constructor for the Enemy class.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     * @param minRuneDrop the minimum number of runes this Enemy drops upon death
     * @param maxRuneDrop the maximum number of runes this Enemy drops upon death
     */
    public CastleEnemy(String name, char displayChar, int hitPoints, int minRuneDrop, int maxRuneDrop) {
        super(displayChar, hitPoints, minRuneDrop, maxRuneDrop, name);
        this.addCapability(EnemyType.CASTLE_TYPE);
        rm.registerResettable(this);
        this.addCapability(StatusActor.CAN_DESPAWN);
    }


    /**
     * Checks if the enemy is removable.
     *
     * @return true if the enemy is removable, false otherwise
     */
    @Override
    public boolean isRemovable() {
        return true;
    }

    /**
     * Resets the CastleEnemy object.
     *
     * @param map  the GameMap object
     * @param rest true if the player is resting, false otherwise
     * @return a String representing the result of the reset action
     */
    @Override
    public String reset(GameMap map, boolean rest) {
        DespawnAction despawn = new DespawnAction();
        return despawn.execute(this, map);
    }

    /**
     * Checks if the enemy is removable when the player rests.
     *
     * @return true if the enemy is removable on player rest, false otherwise
     */
    public boolean isRemovableOnPlayerRest() {
        return true;
    }
}
