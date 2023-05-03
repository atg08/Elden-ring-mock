package game.environments;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.gameactors.enemies.Enemy;
import game.gameactors.enemies.water.GiantCrab;
import game.gameactors.enemies.water.GiantCrayfish;
import game.utils.RandomNumberGenerator;

/**

 Class representing the PuddleOfWater environment, a type of game environment.
 *
 * @author Tanul , Satoshi , Aditti
 * @version 1.0.0
 */
public class PuddleOfWater extends Environment {

    /**
     * Constructor for PuddleOfWater class.
     */
    public PuddleOfWater() {
        super('~');
    }


    /**
     * Spawns an enemy at the given location in the given game map with a certain probability.
     * The type of enemy spawned is determined by the location being east or west of the middle of the map.
     * If the location is east a GiantCrayfish enemy will be spawned.
     * If the location is west GiantCrab enemy will be spawned.
     *
     * @param location the location where the enemy should spawn
     * @param map      the game map in which the enemy is spawned
     * @return an Enemy object representing the enemy that was spawned, or null if no enemy was spawned
     */
    @Override
    public Enemy spawn(Location location, GameMap map) {
        if (detEast(location, map)) {
            // check east or west
            if (RandomNumberGenerator.getBooleanProbability(1)) {
                return new GiantCrayfish();
            }
        } else {
            if (RandomNumberGenerator.getBooleanProbability(2)) {
                return new GiantCrab();

            }
        }
        return null;
    }
}

