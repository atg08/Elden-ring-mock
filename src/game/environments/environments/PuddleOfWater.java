package game.environments;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.gameactors.enemies.Enemy;
//import game.gameactors.enemies.GiantCrab;
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
    public Enemy spawn(int spawnRate) {
        if (RandomNumberGenerator.getBooleanProbability(spawnRate)){
            // check east or west
            // spawn giant crab
            //return new GiantCrab();
            return null;

        }
        return null;
    }

    public void tick(Location location, GameMap gamemap){
        if (location.getActor() == null){
            //spawning
            int spawnRate = 2;
            spawn(spawnRate);
        }
    }
}

