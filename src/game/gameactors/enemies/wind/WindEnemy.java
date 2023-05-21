package game.gameactors.enemies.wind;

import edu.monash.fit2099.engine.positions.GameMap;
import game.reset.Resettable;
import game.actions.DespawnAction;
import game.gameactors.EnemyType;
import game.gameactors.StatusActor;
import game.gameactors.enemies.Enemy;


/**
 * This class represents a Wind-type enemy, which extends the abstract class Enemy and implements the Resettable interface.
 *
 * @author Tanul , Satoshi , Aditti
 * @version 1.0
 * @see Enemy
 * @see Resettable
 */
public abstract class WindEnemy extends Enemy implements Resettable{

    /**
     * Constructor for creating a new WindEnemy object.
     *
     * @param name The name of the WindEnemy.
     * @param displayChar The character that represents the WindEnemy in the game display.
     * @param hitPoints The starting hit points of the WindEnemy.
     * @param minRuneDrop The minimum number of runes that can be dropped by the WindEnemy.
     * @param maxRuneDrop The maximum number of runes that can be dropped by the WindEnemy.
     */
    public WindEnemy(String name, char displayChar, int hitPoints, int minRuneDrop, int maxRuneDrop) {
        super(displayChar, hitPoints, minRuneDrop, maxRuneDrop, name);
        this.addCapability(EnemyType.WIND_TYPE);
        rm.registerResettable(this);
        this.addCapability(StatusActor.CAN_DESPAWN);
    }

    /**
     * Determines if the WindEnemy can be removed.
     *
     * @return true, as WindEnemies can be removed.
     */
    @Override
    public boolean isRemovable() {
        return true;
    }


    /**
     * Resets the WindEnemy by executing a DespawnAction.
     *
     * @param map The game map.
     * @return The result of executing the DespawnAction on the WindEnemy.
     */
    @Override
    public String reset(GameMap map, boolean rest) {
        DespawnAction despawn = new DespawnAction();
        return despawn.execute(this, map);
    }

    public boolean isRemovableOnPlayerRest() {
        return true;
    }
}
