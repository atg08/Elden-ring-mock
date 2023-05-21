package game.environments;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.gameactors.enemies.Enemy;
import game.gameactors.enemies.castle.GodrickSoldier;
import game.utils.RandomNumberGenerator;

/**
 * The Barrack class represents a type of environment called a barrack.
 * It extends the Environment class.
 *
 * @version 1.0
 * @see Environment
 */
public class Barrack extends Environment{


    /**
     * Constructor for creating a Barrack object.
     * It calls the superclass constructor and sets the symbol to 'B'.
     */
    public Barrack() {super('B');}

    /**
     * Spawns Godrick Soldier in the specified location on the game map.
     *
     * @param location the location where the enemy should be spawned
     * @param map the game map where the enemy is being spawned
     * @return the spawned enemy, or null if no enemy is spawned
     */
    @Override
    public Enemy spawn(Location location, GameMap map) {
        if (RandomNumberGenerator.getBooleanProbability(45)) {
            return new GodrickSoldier();
        }
        return null;
    }
}
