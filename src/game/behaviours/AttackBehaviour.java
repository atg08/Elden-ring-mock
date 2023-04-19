package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;

public class AttackBehaviour implements Behaviour {


    @Override
    public Action getAction(Actor actor, GameMap map) {
//        return new AttackAction(actor, 115, G);
        return null;
    }
}
