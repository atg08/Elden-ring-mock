package game.environments;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.gameactors.enemies.Enemy;
import game.gameactors.enemies.castle.Dog;
import game.utils.RandomNumberGenerator;

public class Cage extends Environment {


    /**
     * Constructor.
     */
    public Cage() {super('<');}


    @Override
    public Enemy spawn(Location location, GameMap map) {
        if (RandomNumberGenerator.getBooleanProbability(37)) {
            return new Dog();
        }
        return null;
    }

}


