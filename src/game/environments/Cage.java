package game.environments;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.gameactors.enemies.Enemy;
import game.gameactors.enemies.castle.Dog;
import game.utils.RandomNumberGenerator;

/**
 * The Cage class represents a cage in the game environment.
 * It extends the Environment class.
 *
 * @version 1.0
 * @see Environment
 */
public class Cage extends Environment {

    /**
     * Constructor for the Cage class.
     */
    public Cage() {super('<');}


    /**
     * Spawns a Dog in the specified location on the game map.
     *
     * @param location The location where the enemy should be spawned.
     * @param map      The game map where the enemy should be spawned.
     * @return An instance of the Enemy class representing the spawned enemy, or null if no enemy is spawned.
     * @see Enemy
     * @see Location
     * @see GameMap
     */
    @Override
    public Enemy spawn(Location location, GameMap map) {
        if (RandomNumberGenerator.getBooleanProbability(37)) {
            return new Dog();
        }
        return null;
    }

}


