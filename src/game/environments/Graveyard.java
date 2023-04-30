package game.environments;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.gameactors.enemies.Enemy;

import game.gameactors.enemies.graveyard.HeavySkeletonSwordsman;
import game.gameactors.enemies.graveyard.SkeletalBandit;
import game.utils.RandomNumberGenerator;

public class Graveyard extends Environment{

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Graveyard() {
        super('n');
    }

    @Override
    public Enemy spawn(Location location, GameMap map) {
        if (RandomNumberGenerator.getBooleanProbability(27)) {
            if (detEast(location, map)) {
                return new SkeletalBandit();
            } else {
                return new HeavySkeletonSwordsman();
            }
        }
        return null;
    }


//    public void tick(Location location, GameMap gamemap){
//
//        if (location.getActor() == null){
//            spawn(location,gamemap);
//        }
//    }
}
