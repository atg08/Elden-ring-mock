package game.environments;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.gameactors.enemies.Enemy;
import game.gameactors.enemies.graveyard.HeavySkeletonSwordsman;
import game.gameactors.enemies.graveyard.SkeletalBandit;
import game.gameactors.enemies.graveyard.SkeletalMilitiaman;
import game.gameactors.enemies.graveyard.SkeletonMage;
import game.utils.RandomNumberGenerator;


/**

 Class representing a Graveyard environment, which is a type of Environment object.
 *
 * @author Tanul , Satoshi , Aditti
 * @version 1.0.0
 */
public class Graveyard extends Environment{
    /**
     * Constructor for Graveyard class.
     */
    public Graveyard() {
        super('n');
    }

    /**
     * Spawns an enemy at the given location in the given game map with a certain probability.
     * The type of enemy spawned is determined by the location
     * If the location is east a SkeletonBandit enemy will be spawned.
     * If the location is west HeavySkeletonSwordsman enemy will be spawned.
     *
     * @param location the location where the enemy should spawn
     * @param map the game map in which the enemy is spawned
     * @return an Enemy object representing the enemy that was spawned, or null if no enemy was spawned
     */

    @Override
    public Enemy spawn(Location location, GameMap map) {
        if (RandomNumberGenerator.getBooleanProbability(27)) {
            if (detEast(location, map) && detNorth(location , map)) {
                return new SkeletalBandit();
            } else if (!detEast(location, map) && !detNorth(location,map)) {
                return new HeavySkeletonSwordsman();
            }else if (!detEast(location, map) && detNorth(location,map)) {
                return new SkeletalMilitiaman();
            }else {
                return new SkeletonMage();
            }
        }
        return null;
    }

}
