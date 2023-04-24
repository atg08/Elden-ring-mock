package game.gameactors.enemies.water;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.DespawnAction;
import game.behaviours.Behaviour;
import game.gameactors.EnemyType;

/**
 * A powerful enemy of the player that can perform a powerful slam attack
 * @author tanul
 */

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

    // slam cna be individual or area attack
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(208, "slams", 90);
    }

}
