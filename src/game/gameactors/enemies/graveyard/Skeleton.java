package game.gameactors.enemies.graveyard;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
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
    }

    public Action playTurn(ActionList actions, Action lastAction, GameMap gameMap, Display display){
        if (!this.hasCapability(StatusActor.FOLLOWING) && RandomNumberGenerator.getBooleanProbability(10)){
            return new DespawnAction();
        }

        return new DoNothingAction();
    }

    // add PileOfBones

    @Override
    public void reset(Actor actor, GameMap map) {
        DespawnAction despawn = new DespawnAction();
        despawn.execute(this, map);
    }
}
