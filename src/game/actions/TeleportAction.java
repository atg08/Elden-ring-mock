package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.doors.GoldenFogDoor;
import game.gameactors.players.Player;

import java.util.HashMap;

/**
 * The TeleportAction class represents an action that allows the player to teleport to different locations.
 * It extends the Action class.
 *
 * @version 1.0
 * @see Action
 */
public class TeleportAction extends Action {


    private static HashMap<String,Location> fogDoorLocations = new HashMap<>();

    public static void addFogDoorLocation(String locationName, Location locationOfEndPoint){
       fogDoorLocations.put(locationName,locationOfEndPoint);
    }


    /**
     * Executes the teleport action for the given actor and game map.
     *
     * @param actor the actor performing the teleport action (must be a Player)
     * @param map   the game map on which the teleport action is being executed
     * @return a string indicating that the player has gone to a new map
     * @see Actor
     * @see GameMap
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        Location currentLocation = map.locationOf(actor);

        if (currentLocation.x() >= 4 && currentLocation.x() <= 6 && currentLocation.y() >= 20 && currentLocation.y() <= 22){
            map.moveActor(player,fogDoorLocations.get("LIMGRAVE_ROUNDTABLE"));
        } else if (currentLocation.x() >= 8 && currentLocation.x() <= 10 && currentLocation.y() >= 9 && currentLocation.y() <= 10) {
            map.moveActor(player,fogDoorLocations.get("ROUNDTABLE_LIMGRAVE"));
        } else if (currentLocation.x() >= 28 && currentLocation.x() <= 30 && currentLocation.y() >= 0 && currentLocation.y() <= 1) {
            map.moveActor(player,fogDoorLocations.get("LIMGRAVE_STORMVEIL"));
        }
        else if (currentLocation.x() >= 37 && currentLocation.x() <= 39 && currentLocation.y() >= 22 && currentLocation.y() <= 23){
            map.moveActor(player,fogDoorLocations.get("STORMVEIL_LIMGRAVE"));
        }
        else if (currentLocation.x() >= 14 && currentLocation.x() <= 16 && currentLocation.y() >= 0 && currentLocation.y() <= 1) {
            map.moveActor(player, fogDoorLocations.get("STORMVEIL_BOSSROOM"));
        }

        return "Player has gone to a new map";
    }


    /**
     * Returns a description of the teleport action for the given actor.
     *
     * @param actor the actor performing the teleport action
     * @return a string describing the teleport action for the actor
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " can teleport";
    }
}
