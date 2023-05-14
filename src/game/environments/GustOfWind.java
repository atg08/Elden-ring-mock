package game.environments;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.gameactors.enemies.Enemy;
import game.gameactors.enemies.wind.DireWolf;
import game.gameactors.enemies.wind.GiantDog;
import game.gameactors.enemies.wind.LoneWolf;
import game.gameactors.enemies.wind.RottenStray;
import game.utils.RandomNumberGenerator;


/**

 Class representing the Gust of Wind environment, a type of game environment.
 *
 * @author Tanul , Satoshi , Aditti
 * @version 1.0.0
 */
public class GustOfWind extends Environment{

    /**
     * Constructor for GustOfWind class.
     */
    public GustOfWind() {
        super('&');
    }


    /**
     * Spawns an enemy at the given location in the given game map with a certain probability.
     * The type of enemy spawned is determined by the location
     * If the location is north-east a GiantDog enemy will be spawned.
     * If the location is south-west LoneWolf enemy will be spawned.
     * If the location is north-west a DireWolf enemy will be spawned.
     * If the location is south-east a RottenStay enemy will be spawned.
     *
     * @param location the location where the enemy should spawn
     * @param map the game map in which the enemy is spawned
     * @return an Enemy object representing the enemy that was spawned, or null if no enemy was spawned
     */
    @Override
    public Enemy spawn(Location location, GameMap map) {
        if (detEast(location, map) && detNorth(location , map)) {
            // check east or west
            if (RandomNumberGenerator.getBooleanProbability(4)) {
                return new GiantDog();
            }
        } else if (!detEast(location, map) && !detNorth(location , map)) {
            if (RandomNumberGenerator.getBooleanProbability(33)) {
                return new LoneWolf();

            }
        } else if (!detEast(location, map) && detNorth(location , map)) {
            if (RandomNumberGenerator.getBooleanProbability(26)) {
                return new DireWolf();
            }
        } else {
            if (RandomNumberGenerator.getBooleanProbability(12)) {
                return new RottenStray();
            }
        }
        return null;
    }

}