package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Healer;

/**
 * The HealAction class represents an action where an actor uses a healer item to heal themselves.
 * It extends the Action class.
 *
 * @version 1.0
 * @see Action
 */
public class HealAction extends Action {

    private final Healer healerItem;

    /**
     * Constructs a HealAction object with the specified healer item.
     *
     * @param healerItem the healer item to be used for healing
     */
    public HealAction(Healer healerItem){
        this.healerItem = healerItem;
    }


    /**
     * Executes the heal action on the specified actor using the healer item.
     *
     * @param actor the actor performing the heal action
     * @param map the game map where the action is being executed
     * @return a string representing the result of the heal action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (this.healerItem.isAvailable()){
            actor.heal(this.healerItem.getHealAmount());
            this.healerItem.updateStatus();
            return "The actors health has been restored by " + this.healerItem.getHealAmount();
        }
        return "The healer item was not available";
    }

    /**
     * Returns a description of the heal action for the specified actor.
     *
     * @param actor the actor performing the heal action
     * @return a string describing the heal action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes the " + this.healerItem;
    }
}
