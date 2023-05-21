package game.environments;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.gameactors.enemies.Enemy;
import game.gameactors.enemies.castle.GodrickSoldier;
import game.utils.RandomNumberGenerator;


/**
 * The Barrack class represents a type of environment in the game, that spawns Godrick Soldiers
 * It extends the Environment class.
 *
 * @version 1.0
 * @see Environment
 */
public class Barrack extends Environment{


    /**
     * Constructor for creating a Barrack object.
     */
    public Barrack() {super('B');}


    /**
     * Spawns a Godrick Soldier at the specified location on the given game map.
     *
     * @param location The location where the enemy will be spawned.
     * @param map The game map on which the enemy will be spawned.
     * @return An instance of the Enemy class representing the spawned enemy,
     *         or null if no enemy is spawned.
     * @see Enemy
     */
    @Override
    public Enemy spawn(Location location, GameMap map) {
        if (RandomNumberGenerator.getBooleanProbability(45)) {
            return new GodrickSoldier();
        }
        return null;
    }
}
