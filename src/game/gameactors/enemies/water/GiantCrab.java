package game.gameactors.enemies.water;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.Behaviour;
import game.gameactors.EnemyType;

public class GiantCrab extends OceanEnemy implements Behaviour {
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public GiantCrab() {
        super("Giant Crab", 'C', 208);
    }

    @Override
    public Action getAction(Actor actor, GameMap gameMap) {

        if (actor.hasCapability(EnemyType.OCEAN_TYPE)){
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
