package game.gameactors.players;

import game.weapons.Uchigatana;


/**
 * The Astrologer class extends the Player class and represents an astrologer character in the game.
 *
 * @author Tanul, Satoshi, Aditti
 * @version 1.0
 * @see Player
 */
public class Astrologer extends Player{
    /**
     * Constructs a Player object with the specified number of hitpoints.
     */
    public Astrologer() {
        super(396);
        addWeaponToInventory(new Uchigatana());
        this.originalWeapon = new Uchigatana();
    }
}
