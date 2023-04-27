package game.gameactors.enemies.graveyard;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.Behaviour;
import game.gameactors.EnemyType;

/**
 * A Enemy of the player that holds a weapon called Grossmesser
 * @author tanul
 */

public class SkeletalBandit extends Skeleton implements Behaviour {
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     * @author Tanul
     */
    public SkeletalBandit(String name, char displayChar, int hitPoints) {
        super("Skeletal Bandit", 'b', 184);
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (actor.hasCapability(EnemyType.SKELETON_TYPE)){
            return new DoNothingAction();
        }

//        location = gameMap.locationOf(actor);
        // check exits
        // if there is a player there


//        if (GameMap. player nearby){
//            new AttackBehaviour(new Grossmesser(), )
//        }
        return null;
    }
}
