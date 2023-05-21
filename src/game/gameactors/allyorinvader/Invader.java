package game.gameactors.allyorinvader;

import game.gameactors.enemies.Enemy;
import game.gameactors.players.Player;

/**
 * The Invader class represents an enemy invader in a game.
 * It extends the Enemy class.
 *
 * @version 1.0
 * @see Enemy
 */
public class Invader extends Enemy {

    /**
     * Constructs an Invader object with the specified player.
     *
     * @param player the player object to associate with the invader
     * @return an instance of the Invader class
     */
    public Invader(Player player) {
        super("invader", 'ඞ', player.getMaxHP(), 1358, 5578);
        addWeaponToInventory(player.getWeaponInventory().get(0));
    }
}
