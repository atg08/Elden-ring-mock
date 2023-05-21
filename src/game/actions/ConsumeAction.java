package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Consumable;

/**
 * The ConsumeAction class represents an action where an actor consumes a consumable object.
 * It extends the Action class.
 *
 * @version 1.0
 * @see Action
 */
public class ConsumeAction extends Action {

    private Consumable consumable;


    /**
     * Constructs a new ConsumeAction object with the specified consumable.
     *
     * @param _consumable the consumable object to be consumed
     */
    public ConsumeAction(Consumable _consumable) {
        this.consumable = _consumable;
    }


    /**
     * Executes the consume action by calling the consumeBy() and consume() methods of the consumable object.
     * If the consumeBy() method returns true, it calls the consume() method; otherwise, it returns a message indicating that the actor does nothing.
     *
     * @param actor the actor performing the action
     * @param map the game map on which the action is being performed
     * @return a string indicating the result of the consume action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return consumable.consumeBy(actor) ? consumable.consume(actor) : actor + " does nothing";
    }


    /**
     * Generates a menu description for the consume action.
     *
     * @param actor the actor performing the action
     * @return a string describing the consume action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " can consume " + consumable.toString();
    }
}
