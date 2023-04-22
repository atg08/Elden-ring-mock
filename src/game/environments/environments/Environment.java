package game.environments;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.environments.locations.StatusLocation;
import game.gameactors.StatusActor;
import game.gameactors.enemies.Enemy;
import game.utils.RandomNumberGenerator;

public abstract class Environment extends Ground {
    protected StatusLocation statusLocation;

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Environment(char displayChar) {
        super(displayChar);
    }
    public abstract Enemy spawn(int spawnRate);
}
