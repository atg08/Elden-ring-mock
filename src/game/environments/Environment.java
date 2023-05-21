package game.environments;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.NumberRange;
import game.gameactors.enemies.Enemy;

/**
 *
 Abstract class representing a game environment, which is a type of Ground object.
 *
 * @author Tanul , Satoshi , Aditti
 * @version 1.0.0
 */

public abstract class Environment extends Ground {
    Display display = new Display();

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Environment(char displayChar) {
        super(displayChar);
    }

    /**
     * Abstract method that spawns an enemy at the given location in the given game map.
     *
     * @param location the location where the enemy should spawn
     * @param map the game map in which the enemy is spawned
     * @return an Enemy object representing the enemy that was spawned
     */
    public abstract Enemy spawn(Location location, GameMap map);

    /**
     * Determines if a location is east the map.
     *
     * @param location the location to be checked
     * @param map the game map containing the location
     * @return true if the location is east of the middle of the map, false if it is the west side
     */
    public Boolean detEast (Location location, GameMap map){
        NumberRange widths = map.getXRange();
        // East
        // assume middle is part of west
        //return widths.max()/2 < location.x();
        return location.x() > widths.max()/2;
    }

    /**
     * Determines if a location is north the map.
     *
     * @param location the location to be checked
     * @param map the game map containing the location
     * @return true if the location is north of the middle of the map, false if it is the south side
     */
    public Boolean detNorth (Location location, GameMap map){
        NumberRange lengths = map.getYRange();
        // East
        // assume middle is part of south
        //return lengths.max()/2 > location.y();
        return location.y() < lengths.max()/2;
    }

    /**
     * Overrides the tick method in Ground. Spawns an enemy at the location if there is no actor at the location.
     *
     * @param location the location to be ticked
     */
    @Override
    public void tick(Location location){
        GameMap map = location.map();

        if (!map.isAnActorAt(location)){
            Enemy enemy = spawn(location,map);
            if (enemy != null){
                map.addActor(enemy, location);
                System.out.println(location.x() + " " + location.y());
                display.println(enemy + " spawned");
            }
        }
    }
}
