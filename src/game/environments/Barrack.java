package game.environments;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.gameactors.enemies.Enemy;
import game.gameactors.enemies.castle.GodrickSoldier;
import game.utils.RandomNumberGenerator;

public class Barrack extends Environment{


    /**
     * Constructor.
     */
    public Barrack() {super('B');}

    @Override
    public Enemy spawn(Location location, GameMap map) {
        if (RandomNumberGenerator.getBooleanProbability(45)) {
            return new GodrickSoldier();
        }
        return null;
    }
}
