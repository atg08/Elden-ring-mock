package game.environments;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.gameactors.enemies.Enemy;
import game.gameactors.enemies.HeavySkeletonSwordsman;
import game.utils.RandomNumberGenerator;

public class Graveyard extends Environment{

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Graveyard() {
        super('n');
//        this.statusLocation =  StatusLocation.WEST;
    }

    @Override
    public Enemy spawn(int spawnRate) {
        if (RandomNumberGenerator.getBooleanProbability(spawnRate)){
            // check east or west
            // spawn hss
            return new HeavySkeletonSwordsman();

        }
        return null;
    }

    public void tick(Location location, GameMap gamemap){


        if (location.getActor() == null){
            //spawning
            int spawnRate = 27;
            spawn(spawnRate);
        }

        // despawning
        despawn(location, gamemap);
    }
}
