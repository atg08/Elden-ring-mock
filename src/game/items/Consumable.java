package game.items;

import edu.monash.fit2099.engine.actors.Actor;


/**
 * The Consumable interface represents an item that can be consumed by an actor.
 * It defines methods for consuming the item and checking if it can be consumed by a specific actor.
 *
 * @version 1.0
 * @see Actor
 */
public interface Consumable {


    /**
     * Consumes the item using the provided actor.
     *
     * @param actor the actor consuming the item
     * @return a string representing the result of the consumption
     */
    String consume(Actor actor);


    /**
     * Checks if the object can be consumed by the specified actor.
     *
     * @param actor the actor to check against
     * @return true if the object can be consumed by the actor, false otherwise
     */
    Boolean consumeBy(Actor actor);
}
