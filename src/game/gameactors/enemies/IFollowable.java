package game.gameactors.enemies;

import edu.monash.fit2099.engine.positions.Location;


/**
 * The IFollowable interface represents an object that can be followed and provides access to the player's previous location.
 *
 * @version 1.0
 * @see Location
 */
public interface IFollowable {
    Location getPlayerPreviousLocation();
}
