package game.gameactors.enemies.wind;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.Behaviour;
import game.gameactors.EnemyType;

public class GiantDog extends WindEnemy implements Behaviour {
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     * @author Tanul
     */
    public GiantDog(String name, char displayChar, int hitPoints) {
        super("Giant Dog", 'G', 693);
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (actor.hasCapability(EnemyType.WIND_TYPE)) {
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

    // head
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(314, "bashes", 90);
    }
}
