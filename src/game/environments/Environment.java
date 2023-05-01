package game.environments;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.NumberRange;
import game.gameactors.StatusActor;
import game.gameactors.enemies.Enemy;
import game.gameactors.players.Player;
import game.utils.RandomNumberGenerator;

public abstract class Environment extends Ground {
    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Environment(char displayChar) {
        super(displayChar);
    }

    public abstract Enemy spawn(Location location, GameMap map);

    public Boolean detEast (Location location, GameMap map){
        NumberRange widths = map.getXRange();
        // East
        // assume middle is part of west
        return widths.max()/2 < location.x();
    }

    @Override
    public void tick(Location location){
        GameMap map = location.map();

        if (!map.isAnActorAt(location)){
            Enemy enemy = spawn(location,map);
            if (enemy != null){
                map.addActor(enemy, location);
                System.out.println("comes here");
            }
        }
    }
}
