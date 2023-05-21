package game.environments;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.gameactors.enemies.Enemy;
import game.gameactors.enemies.water.GiantBat;
import game.gameactors.enemies.water.GiantCrab;
import game.gameactors.enemies.water.GiantCrayfish;
import game.gameactors.enemies.water.GiantDragonfly;
import game.utils.RandomNumberGenerator;

/**

 Class representing the PuddleOfWater environment, a type of game environment.
 *
 * @author Tanul , Satoshi , Aditti
 * @version 1.0.0
 * @see Environment
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
     * The type of enemy spawned is determined by the location.
     * If the location is north-east a GiantCrayfish enemy will be spawned.
     * If the location is south-west GiantCrab enemy will be spawned.
     * If the location is north-west GiantBat enemy will be spawned.
     * If the location is north-east GiantDragonfly enemy will be spawned.
     *
     * @param location the location where the enemy should spawn
     * @param map      the game map in which the enemy is spawned
     * @return an Enemy object representing the enemy that was spawned, or null if no enemy was spawned
     */
    @Override
    public Enemy spawn(Location location, GameMap map) {
        if (detEast(location, map) && detNorth(location , map)) {
            if (RandomNumberGenerator.getBooleanProbability(1)) {
                return new GiantCrayfish();
            }
        } else if (!detEast(location, map) && !detNorth(location , map)) {
            if (RandomNumberGenerator.getBooleanProbability(2)) {
                return new GiantCrab();
            }
        } else if (!detEast(location, map) && detNorth(location , map)) {
            if (RandomNumberGenerator.getBooleanProbability(3)) {
                return new GiantBat();
            }
        } else {
            if (RandomNumberGenerator.getBooleanProbability(4)) {
                return new GiantDragonfly();
            }
        }
        return null;
    }
}

