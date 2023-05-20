package game.environments;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.gameactors.enemies.Enemy;
import game.gameactors.enemies.earth.GlintstoneSorcerer;
import game.gameactors.enemies.earth.TrollKnight;
import game.utils.RandomNumberGenerator;


/**

 Class representing the Gust of Earth environment, a type of game environment.
 *
 * @author Tanul , Satoshi , Aditti
 * @version 1.0.0
 */
public class TrevorOfEarth extends Environment{
    /**
     * Constructor for TrevorOfEarth
     */
    public TrevorOfEarth() {
        super('I');
    }


    /**
     * Spawns an enemy at the given location in the given game map with a certain probability.
     * The type of enemy spawned is determined by the location
     * If the location is north-east or north-west a TrollKnight enemy will be spawned.
     * If the location is south-east or south-west GlintstoneSorcerer enemy will be spawned.
     *
     * @param location the location where the enemy should spawn
     * @param map the game map in which the enemy is spawned
     * @return an Enemy object representing the enemy that was spawned, or null if no enemy was spawned
     */
    @Override
    public Enemy spawn(Location location, GameMap map) {
        if (detEast(location, map) && detNorth(location, map) || !detEast(location, map) && detNorth(location, map)) {
            if (RandomNumberGenerator.getBooleanProbability(1)) {
                return new TrollKnight();
            }
        } else if (!detEast(location, map) && !detNorth(location, map) || detEast(location, map) && !detNorth(location, map)) {
            if (RandomNumberGenerator.getBooleanProbability(4)) {
                return new GlintstoneSorcerer();
            }
        }
        return null;
    }
}
