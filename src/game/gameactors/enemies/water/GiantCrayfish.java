package game.gameactors.enemies.water;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.Behaviour;
import game.gameactors.EnemyType;

public class GiantCrayfish extends OceanEnemy implements Behaviour {
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     * @author Tanul
     */
    public GiantCrayfish() {
        super("Giant Crayfish", 'R', 4803);
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {

        if (actor.hasCapability(EnemyType.OCEAN_TYPE)) {
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

    // giant Pincer
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(527, "slams", 100);
    }
}
