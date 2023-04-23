package game.environments;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.gameactors.enemies.Enemy;
//import game.gameactors.enemies.GiantCrab;
import game.gameactors.enemies.water.GiantCrab;
import game.gameactors.enemies.water.GiantCrayfish;
import game.gameactors.enemies.wind.GiantDog;
import game.gameactors.enemies.wind.LoneWolf;
import game.utils.RandomNumberGenerator;

public class PuddleOfWater extends Environment {

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public PuddleOfWater(char displayChar) {
        super('~');
    }

    @Override
    public Enemy spawn(Location location, GameMap map) {
        if (detEast(location, map)) {
            // check east or west
            if (RandomNumberGenerator.getBooleanProbability(1)) {
                // spawn skeleton bandit
                return new GiantCrayfish();
            }
        } else {
            if (RandomNumberGenerator.getBooleanProbability(2)) {
                return new GiantCrab();

            }
        }
        return null;
    }
//    public void tick(Location location, GameMap gamemap){
//        if (location.getActor() == null){
//            //spawning
//            int spawnRate = 2;
//            spawn(spawnRate);
//        }
//    }
}

