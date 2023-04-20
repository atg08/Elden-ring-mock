package game.gameactors.enemies.water;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.DespawnAction;
import game.gameactors.StatusActor;
import game.gameactors.enemies.Enemy;
import game.utils.RandomNumberGenerator;

public abstract class OceanEnemy extends Enemy {
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public OceanEnemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }

    public Action playTurn(ActionList actions, Action lastAction, GameMap gameMap, Display display){
        if (!this.hasCapability(StatusActor.FOLLOWING) && RandomNumberGenerator.getBooleanProbability(10)){
            return new DespawnAction();
        }

        return new DoNothingAction();
    }

}