package game.gameactors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Reset.ResetManager;
import game.Reset.Resettable;
import game.gameactors.StatusActor;
import game.utils.RandomNumberGenerator;

public class Enemy extends Actor {
    protected ResetManager rm = ResetManager.getInstance();
//    protected StatusActor enemyType;
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        addCapability(StatusActor.IS_ENEMY);
    }


    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return null;
    }

//    public StatusActor getEnemyType(){
//        return this.enemyType;
//    }
}
