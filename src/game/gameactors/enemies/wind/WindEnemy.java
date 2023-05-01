package game.gameactors.enemies.wind;

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
import game.gameactors.players.Player;
import game.utils.RandomNumberGenerator;


/**
 * A Abstract class that defines implementation of the similar characteristics of the WIND_TYPE enemies
 * @author tanul
 */
public abstract class WindEnemy extends Enemy implements Resettable{

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public WindEnemy(String name, char displayChar, int hitPoints, int minRuneDrop, int maxRuneDrop) {
        super(name, displayChar, hitPoints, minRuneDrop, maxRuneDrop);
        this.addCapability(EnemyType.WIND_TYPE);
        rm.registerResettable(this);
        this.addCapability(StatusActor.CAN_DESPAWN);
    }
    @Override
    public boolean isRemovable() {
        return true;
    }

    @Override
    public String reset(Actor actor, GameMap map) {
        DespawnAction despawn = new DespawnAction();
        return despawn.execute(this, map);
    }
}
