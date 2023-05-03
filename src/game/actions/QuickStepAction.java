package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.gameactors.EnemyType;

import java.util.ArrayList;
import java.util.Random;

/**
 * Action for quick step.
 * @author Satoshi Kashima
 */
public class QuickStepAction extends Action {

    /**
     * The Actor that is to be attacked
     */
    private Actor target;

    /**
     * The direction of incoming attack.
     */
    private String direction;

    /**
     * Random number generator
     */
    private Random rand = new Random();

    /**
     * Weapon used for the attack
     */
    private Weapon weapon;

    /**
     * Constructor with weapon.
     *
     * @param weapon the weapon used to attack the target
     * @param target the Actor to attack
     * @param direction the direction where the attack should be performed (only used for display purposes)
     */
    public QuickStepAction(Actor target, String direction, Weapon weapon) {
        this.target = target;
        this.direction = direction;
        this.weapon = weapon;
    }

    /**
     * Constructor with intrinsic weapon as default
     *
     * @param target the actor to attack
     * @param direction the direction where the attack should be performed (only used for display purposes)
     */
    public QuickStepAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    /**
     * Executes the quick step action. Uses AttackAction and MoveActorAction internally.
     *
     * @param actor The actor performing the attack action.
     * @param map The map the actor is on.
     * @return the result of the attack, e.g. whether the target is killed, etc.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String attackResult = new AttackAction(this.target, this.direction, this.weapon).execute(actor, map);
        ArrayList<Action> actions = new ArrayList<>();

        // find possible locations to make a quick step
        for (Exit exit : map.locationOf(actor).getExits()){
            Location destination = exit.getDestination();
            if (destination.getActor() == null && destination.canActorEnter(actor)){
                actions.add(new MoveActorAction(destination, exit.getName()));
            }
        }

        // randomly choose where to move
        if (actions.size() != 0){
            String moveResult = actions.get(rand.nextInt(actions.size())).execute(actor, map);
            return System.lineSeparator() + attackResult + System.lineSeparator() + moveResult;
        }

        return attackResult + System.lineSeparator() + "but there was no place to move to...";
    }

    /**
     * Describes which target the actor is attacking with which weapon
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " and moves away";
    }
}
