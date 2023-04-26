package game.gameactors.enemies.graveyard;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Reset.ResetManager;
import game.Reset.Resettable;
import game.actions.DespawnAction;
import game.gameactors.EnemyType;
import game.gameactors.StatusActor;
import game.gameactors.enemies.Enemy;
import game.utils.RandomNumberGenerator;

/**
 * A Abstract class that defines implementation of the similar characteristics of the SKELETON_TYPE enemies
 * @author tanul
 */

public abstract class Skeleton extends Enemy implements Resettable{

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Skeleton(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        addCapability(EnemyType.SKELETON_TYPE);
        rm.registerResettable(this);
    }

    public Action playTurn(ActionList actions, Action lastAction, GameMap gameMap, Display display){
        if (!this.hasCapability(EnemyType.FOLLOWING) && RandomNumberGenerator.getBooleanProbability(10)){
            rm.removeResettable(this);
            return new DespawnAction();
        }

        return new DoNothingAction();
    }

    // add PileOfBones


    // in skeleton for extensibility and modifications incase future enemies are not resettable
    @Override
    public String reset(Actor actor, GameMap map) {
        DespawnAction despawn = new DespawnAction();
        // this is so that enemy that has been removed
        // won't be attempted to be removed again
        return despawn.execute(this, map);
    }

    @Override
    public boolean isRemovable() {
        return true;
    }



}
