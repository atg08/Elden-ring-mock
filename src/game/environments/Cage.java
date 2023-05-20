package game.environments;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.gameactors.enemies.Enemy;
import game.gameactors.enemies.castle.Dog;
import game.utils.RandomNumberGenerator;


/**
 * The Cage class represents a specific type of environment called Cage, that spawns Dogs,
 * which is a subclass of the Environment class.
 *
 * @version 1.0
 * @see Environment
 */
public class Cage extends Environment {


    /**
     * Constructs a Cage object.
     */
    public Cage() {super('<');}


    /**
     * Spawns a Dog in the specified location on the given GameMap.
     *
     * @param location The location where the Enemy will be spawned.
     * @param map The GameMap on which the Enemy will be spawned.
     * @return The Enemy object spawned at the specified location, or null if no Enemy is spawned.
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


