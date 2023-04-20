package game.environments;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.gameactors.enemies.Enemy;
//import game.gameactors.enemies.LoneWolf;
import game.utils.RandomNumberGenerator;

public class GustOfWind extends Environment{

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public GustOfWind(char displayChar) {
        super('&');
    }

    @Override
    public Enemy spawn(int spawnRate) {
        if (RandomNumberGenerator.getBooleanProbability(spawnRate)){
            // check east or west
            // spawn lone wolf
            //return new LoneWolf();
            return null;

        }
        return null;
    }

    public void tick(Location location, GameMap gamemap){

        if (location.getActor() == null){
            //spawning
            int spawnRate = 33;
            spawn(spawnRate);
        }
    }
}
