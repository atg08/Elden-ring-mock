package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.doors.GoldenFogDoor;
import game.gameactors.players.Player;

public class TeleportAction extends Action {


    private final Location ROUNDTABLE_LIMGRAVE = Player.getMapsAccessible().get(0).at(5,21);
    private final Location LIMGRAVE_ROUNDTABLE = Player.getMapsAccessible().get(2).at(9,10);
    private final Location STORMVEIL_LIMGRAVE = Player.getMapsAccessible().get(0).at(29, 0);
    private final Location LIMGRAVE_STORMVEIL = Player.getMapsAccessible().get(1).at(38, 23);

    private final Location STORMVEIL_BOSSROOM = Player.getMapsAccessible().get(3).at(0,3);

//    private final Location

    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        Location currentLocation = map.locationOf(actor);
//        player.getMapsAccessible(); // arraylist of accessible maps
        //roundtable door at 6,22
        if (currentLocation.x() >= 4 && currentLocation.x() <= 6 && currentLocation.y() >= 20 && currentLocation.y() <= 22){
//            Location roundTableDoor = new Location();

//            LIMGRAVE_ROUNDTABLE.setGround(new GoldenFogDoor());
//            map.removeActor(player);
//            player.getMapsAccessible().get(1).addActor(player,roundTableDoor);
            map.moveActor(player,LIMGRAVE_ROUNDTABLE);
//            System.out.println(player.getMapsAccessible().get(1).getActorAt(roundTableDoor));
        } else if (currentLocation.x() >= 8 && currentLocation.x() <= 10 && currentLocation.y() >= 9 && currentLocation.y() <= 10) {
//            ROUNDTABLE_LIMGRAVE.setGround(new GoldenFogDoor());
            map.moveActor(player,ROUNDTABLE_LIMGRAVE);
        } else if (currentLocation.x() >= 28 && currentLocation.x() <= 30 && currentLocation.y() >= 0 && currentLocation.y() <= 1) {
            map.moveActor(player,LIMGRAVE_STORMVEIL);
        }

        else if (currentLocation.x() >= 37 && currentLocation.x() <= 39 && currentLocation.y() >= 22 && currentLocation.y() <= 23){
            map.moveActor(player,STORMVEIL_LIMGRAVE);
        }
        else if (currentLocation.x() >= 14 && currentLocation.x() <= 16 && currentLocation.y() >= 0 && currentLocation.y() <= 1) {
            map.moveActor(player, STORMVEIL_BOSSROOM);
        }



        return "Player has gone to a new map";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " can teleport";
    }
}
