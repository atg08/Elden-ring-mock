package game.gameactors.enemies.castle;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.DespawnAction;
import game.gameactors.EnemyType;
import game.gameactors.StatusActor;
import game.gameactors.enemies.Enemy;
import game.reset.Resettable;

/**
 * This class represents an abstract CastleEnemy that extends the Enemy class
 * and implements the Resettable interface.
 *
 * @version 1.0
 * @see Enemy
 * @see Resettable
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
        super(name, displayChar, hitPoints, minRuneDrop, maxRuneDrop);
        this.addCapability(EnemyType.CASTLE_TYPE);
        rm.registerResettable(this);
        this.addCapability(StatusActor.CAN_DESPAWN);
    }


    /**
     * Checks if the CastleEnemy is removable.
     *
     * @return true if the CastleEnemy is removable, false otherwise
     */
    @Override
    public boolean isRemovable() {
        return true;
    }

    /**
     * Resets the CastleEnemy.
     *
     * @param actor the Actor to reset
     * @param map   the GameMap the Actor belongs to
     * @return a String representing the result of the reset operation
     * @see DespawnAction
     */
    @Override
    public String reset(Actor actor, GameMap map) {
        DespawnAction despawn = new DespawnAction();
        return despawn.execute(this, map);
    }
}
