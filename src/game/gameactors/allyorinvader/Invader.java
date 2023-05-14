package game.gameactors.allyorinvader;

import game.gameactors.enemies.Enemy;
import game.gameactors.players.Player;

public class Invader extends Enemy {
    /**
     * Constructor for the Enemy class.
     */
    public Invader(Player player) {
        super("invader", 'ඞ', player.getMaxHP(), 1358, 5578);
        addWeaponToInventory(player.getWeaponInventory().get(0));
    }
}
