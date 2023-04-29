package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.gameactors.EnemyType;

import java.util.Random;

public class UnsheatheAction extends Action {

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
     * Constructor.
     *
     * @param target the Actor to attack
     * @param direction the direction where the attack should be performed (only used for display purposes)
     */
    public UnsheatheAction(Actor target, String direction, Weapon weapon) {
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
    public UnsheatheAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }



    /**
     * Describes which target the actor is attacking with which weapon
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " at " + direction + " with " + (weapon != null ? weapon : "Uchigatana" + " with Unsheathe Action");
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        int damage = 2 * (weapon.damage());
        String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
        target.hurt(damage);
        if (!target.isConscious() && !target.hasCapability(EnemyType.SKELETON_TYPE)) {
            result += new DeathAction(actor).execute(target, map);
        }else if (!target.isConscious() && target.hasCapability(EnemyType.SKELETON_TYPE)) {
            result += new TurnIntoPileOfBonesAction().execute(target, map);
        }

        return result;
    }


}
