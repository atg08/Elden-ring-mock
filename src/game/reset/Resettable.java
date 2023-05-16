package game.reset;

import edu.monash.fit2099.engine.positions.GameMap;

/**

 An interface for objects that can be reset to their initial state.

 Created by: Adrian Kristanto
 */

public interface Resettable {
    /**
     * Resets the state of the object to its initial state.
     *
     * @param map the game map on which the actor is present
     * @return a message describing the result of the reset process
     */
    String reset(GameMap map, boolean rest);

    /**

     Checks whether the object can be removed after reset.
     @return true if the object can be removed, false otherwise
     */
    boolean isRemovable();

    boolean isRemovableOnPlayerRest();

}
