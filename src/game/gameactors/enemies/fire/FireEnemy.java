package game.gameactors.enemies.fire;

import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.DespawnAction;
import game.gameactors.EnemyType;
import game.gameactors.StatusActor;
import game.gameactors.enemies.Enemy;
import game.reset.Resettable;


/**
 * This abstract class represents an enemy that can be found in a fire environment in a game.
 * It extends the Enemy class and implements the Resettable interface.
 *
 * @author Tanul , Satoshi , Aditti
 * @version 1.0
 * @see Enemy
 * @see Resettable
 */
public abstract class FireEnemy extends Enemy implements Resettable {
    /**
     * Constructor for the Enemy class.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     * @param minRuneDrop the minimum number of runes this Enemy drops upon death
     * @param maxRuneDrop the maximum number of runes this Enemy drops upon death
     */
    public FireEnemy(String name, char displayChar, int hitPoints, int minRuneDrop, int maxRuneDrop) {
        super(displayChar, hitPoints, minRuneDrop, maxRuneDrop, name);
        this.addCapability(EnemyType.FIRE_TYPE);
        rm.registerResettable(this);
        this.addCapability(StatusActor.CAN_DESPAWN);
    }

    /**
     * Resets the FireEnemy to its initial state.
     *
     * @param actor the actor to reset
     * @param map   the game map the actor is on
     * @return a string message indicating the outcome of the reset
     */
    @Override
    public String reset(GameMap map, boolean rest) {
        DespawnAction despawn = new DespawnAction();
        return despawn.execute(this, map);
    }


    /**
     * Determines whether the FireEnemy can be removed from the game.
     *
     * @return true if the FireEnemy can be removed, false otherwise
     */
    @Override
    public boolean isRemovable() {
        return true;
    }

    @Override
    public boolean isRemovableOnPlayerRest() {
        return true;
    }
}
