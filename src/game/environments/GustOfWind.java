package game.environments;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.gameactors.enemies.Enemy;
//import game.gameactors.enemies.LoneWolf;
import game.gameactors.enemies.graveyard.HeavySkeletonSwordsman;
import game.gameactors.enemies.graveyard.SkeletalBandit;
import game.gameactors.enemies.wind.GiantDog;
import game.gameactors.enemies.wind.LoneWolf;
import game.gameactors.players.Player;
import game.utils.RandomNumberGenerator;

public class GustOfWind extends Environment{

    /**
     * Constructor.
     */
    public GustOfWind() {
        super('&');
    }

    @Override
    public Enemy spawn(Location location, GameMap map) {
        if (detEast(location, map)) {
            // check east or west
            if (RandomNumberGenerator.getBooleanProbability(4)) {
                return new GiantDog();
            }
        } else {
            if (RandomNumberGenerator.getBooleanProbability(33)) {
                return new LoneWolf();

            }
        }
        return null;
    }

//    public void tick(Location location, GameMap gamemap){
//
//            if (location.getActor() == null){
//                spawn(location,gamemap);
//            }
//        }
}
