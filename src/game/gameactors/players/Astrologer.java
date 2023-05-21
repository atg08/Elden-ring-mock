package game.gameactors.players;

import game.weapons.Uchigatana;

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
