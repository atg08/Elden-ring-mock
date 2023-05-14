package game.environments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.DeathAction;
import game.gameactors.StatusActor;
import game.gameactors.enemies.Enemy;
import game.gameactors.enemies.fire.BlackflameMonk;
import game.gameactors.enemies.fire.FireMonk;
import game.utils.RandomNumberGenerator;


/**
 Class representing theFlare Of Inferno environment, a type of game environment.
 *
 * @author Tanul , Satoshi , Aditti
 * @version 1.0.0
 */
public class FlareOfInferno extends Environment{

    /**
     * Constructor for FlareOfInferno
     */
    public FlareOfInferno() {
        super('W');
    }


    /**
     * Spawns an enemy at the given location in the given game map with a certain probability.
     * The type of enemy spawned is determined by the location
     * If the location is north-east or north-west a FireMonk enemy will be spawned.
     * If the location is south-east or south-west BlackflameMonk enemy will be spawned.
     *
     * @param location the location where the enemy should spawn
     * @param map the game map in which the enemy is spawned
     * @return an Enemy object representing the enemy that was spawned, or null if no enemy was spawned
     */
    @Override
    public Enemy spawn(Location location, GameMap map) {
        if (detEast(location, map) && detNorth(location, map) || !detEast(location, map) && detNorth(location, map)) {
            // check east or west
            if (RandomNumberGenerator.getBooleanProbability(3)) {
                return new FireMonk();
            }
        } else if (!detEast(location, map) && !detNorth(location, map) || detEast(location, map) && !detNorth(location, map)) {
            if (RandomNumberGenerator.getBooleanProbability(8)) {
                return new BlackflameMonk();
            }
        }
        return null;
    }


//    @Override
//    public void tick(Location location) {
//        // if player -> trigger death action
//        Actor actor = location.getActor();
//        if (actor != null && actor.hasCapability(StatusActor.IS_PLAYER)){
//            String result = new DeathAction(actor).execute(actor, location.map());
//            new Display().println(result);
//        }
//    }
}
