package game.gameactors.enemies.graveyard;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.reset.Resettable;
import game.actions.DespawnAction;
import game.gameactors.EnemyType;
import game.gameactors.StatusActor;
import game.gameactors.enemies.Enemy;
import game.gameactors.enemies.Revivable;

public abstract class Skeleton extends Enemy implements Revivable, Resettable {
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     * @param minRuneDrop
     * @param maxRuneDrop
     */
    public Skeleton(String name, char displayChar, int hitPoints, int minRuneDrop, int maxRuneDrop) {
        super(name, displayChar, hitPoints, minRuneDrop, maxRuneDrop);
        this.addCapability(EnemyType.SKELETON_TYPE);
        rm.registerResettable(this);
        this.addCapability(StatusActor.CAN_DESPAWN);
    }


    // in skeleton for extensibility and modifications incase future enemies are not resettable
    @Override
    public String reset(Actor actor, GameMap map) {
        DespawnAction despawn = new DespawnAction();
        // this is so that enemy that has been removed
        // won't be attempted to be removed again
        return despawn.execute(this, map);
    }

    @Override
    public Revivable revive() {
        return null;
    }

    @Override
    public boolean isRemovable() {
        return true;
    }



}
