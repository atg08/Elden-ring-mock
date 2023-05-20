package game.gameactors.enemies.earth;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.DespawnAction;
import game.gameactors.EnemyType;
import game.gameactors.StatusActor;
import game.gameactors.enemies.Enemy;
import game.reset.Resettable;

public abstract class EarthEnemy extends Enemy implements Resettable {
    /**
     * Constructor for the EarthEnemy abstract class.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     * @param minRuneDrop the minimum number of runes this Enemy drops upon death
     * @param maxRuneDrop the maximum number of runes this Enemy drops upon death
     */
    public EarthEnemy(String name, char displayChar, int hitPoints, int minRuneDrop, int maxRuneDrop) {
        super(name, displayChar, hitPoints, minRuneDrop, maxRuneDrop);
        this.addCapability(EnemyType.EARTH_TYPE);
        rm.registerResettable(this);
        this.addCapability(StatusActor.CAN_DESPAWN);
    }

    /**
     * Resets the EarthEnemy to its initial state.
     *
     * @param map   the game map the actor is on
     * @return a string message indicating the outcome of the reset
     */
    @Override
    public String reset(GameMap map, boolean rest) {
        DespawnAction despawn = new DespawnAction();
        return despawn.execute(this, map);
    }


    /**
     * Determines whether the EarthEnemy can be removed from the game.
     *
     * @return true if the EarthEnemy can be removed, false otherwise
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
