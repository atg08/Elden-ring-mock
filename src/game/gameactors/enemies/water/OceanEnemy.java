package game.gameactors.enemies.water;

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
 * A Abstract class that defines implementation of the similar characteristics of the OCEAN_TYPE enemies
 * @author tanul
 */
public abstract class OceanEnemy extends Enemy{

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public OceanEnemy(String name, char displayChar, int hitPoints, int minMaxDrop, int maxDropRune) {
        super(name, displayChar, hitPoints, minMaxDrop, maxDropRune);
        this.addCapability(EnemyType.OCEAN_TYPE);
    }


}
