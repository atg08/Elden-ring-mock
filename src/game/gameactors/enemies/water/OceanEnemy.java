package game.gameactors.enemies.water;

import edu.monash.fit2099.engine.positions.GameMap;
import game.reset.Resettable;
import game.actions.DespawnAction;
import game.gameactors.EnemyType;
import game.gameactors.StatusActor;
import game.gameactors.enemies.Enemy;

/**
 * This abstract class represents an enemy that can be found in an ocean environment in a game.
 * It extends the Enemy class and implements the Resettable interface.
 *
 * @author Tanul , Satoshi , Aditti
 * @version 1.0.0
 */
public abstract class OceanEnemy extends Enemy implements Resettable{

    /**
     * Constructor for creating an instance of OceanEnemy.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     * @param minMaxDrop  the minimum amount of gold that the Actor can drop
     * @param maxDropRune the maximum amount of gold that the Actor can drop
     * @return an instance of OceanEnemy
     */
    public OceanEnemy(String name, char displayChar, int hitPoints, int minMaxDrop, int maxDropRune) {
        super(name, displayChar, hitPoints, minMaxDrop, maxDropRune);
        this.addCapability(EnemyType.OCEAN_TYPE);
        rm.registerResettable(this);
        this.addCapability(StatusActor.CAN_DESPAWN);
    }

    /**
     * Resets the OceanEnemy to its initial state.
     *
     * @param map the game map the actor is on
     * @return a string message indicating the outcome of the reset
     */
    @Override
    public String reset(GameMap map , boolean rest) {
        DespawnAction despawn = new DespawnAction();
        return despawn.execute(this, map);
    }


    /**
     * Determines whether the OceanEnemy can be removed from the game.
     *
     * @return true if the OceanEnemy can be removed, false otherwise
     */
    @Override
    public boolean isRemovable() {
        return true;
    }

    public boolean isRemovableOnPlayerRest() {
        return true;
    }
}
