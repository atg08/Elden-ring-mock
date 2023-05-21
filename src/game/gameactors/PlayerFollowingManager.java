package game.gameactors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.gameactors.enemies.NPC;
import game.gameactors.players.Player;

import java.util.List;

public class PlayerFollowingManager {
    private final Player player;

    public PlayerFollowingManager(Player player){
        this.player = player;
    }

    public void updateFollowingStatusIfNeeded(NPC npc, GameMap map){
        if (!npc.hasCapability(StatusActor.FOLLOWING) && this.wasPlayerAround(map.locationOf(npc).getExits())){
            npc.addCapability(StatusActor.FOLLOWING);
        }
    }

    private boolean wasPlayerAround(List<Exit> exits){
        for (Exit exit: exits){
            Location destination = exit.getDestination();
            if (destination.getActor() == this.player){
                return true;
            };
//            return destination == this.player.getPlayerPreviousLocation();
        }

        return false;
    }
}
