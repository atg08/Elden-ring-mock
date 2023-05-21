package game.gameactors;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.gameactors.enemies.NPC;
import game.gameactors.players.Player;

import java.util.List;

/**
 * The PlayerFollowingManager class is responsible for managing the following status of an NPC towards a player.
 * It provides methods to update the following status based on the player's presence.
 *
 * @author Satoshi
 * @version 1.0
 * @see Player
 * @see NPC
 * @see GameMap
 */
public class PlayerFollowingManager {
    private final Player player;

    /**
     * Constructs a PlayerFollowingManager object with the specified player.
     *
     * @param player the player to be followed by NPCs
     */
    public PlayerFollowingManager(Player player){
        this.player = player;
    }

    /**
     * Updates the following status of the given NPC if needed, based on the player's presence in the game map.
     *
     * @param npc  the NPC whose following status needs to be updated
     * @param map  the game map in which the NPC and the player are located
     */
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
