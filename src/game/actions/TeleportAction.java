package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.doors.GoldenFogDoor;
import game.gameactors.players.Player;

import java.util.HashMap;

public class TeleportAction extends Action {


    private static HashMap<String,Location> fogDoorLocations = new HashMap<>();

    public static void addFogDoorLocation(String locationName, Location locationOfEndPoint){
       fogDoorLocations.put(locationName,locationOfEndPoint);
    }

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

    @Override
    public String menuDescription(Actor actor) {
        return actor + " can teleport";
    }
}
