package game.reset;

import edu.monash.fit2099.engine.positions.GameMap;

/**

 The Respawnable interface provides a contract for objects that can be respawned on a GameMap after being removed.
 Created by: tanul
 @author tanul
 Modified by:
 */
public interface Respawnable {

    /**
     * This method is called when an object implementing this interface needs to respawn on a given GameMap.
     *
     * @param map the GameMap on which the object should respawn
     */
    public void respawn(GameMap map);
}
