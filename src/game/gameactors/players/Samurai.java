package game.gameactors.players;

import game.weapons.Uchigatana;

/**
 * This is the concrete class for Samurai
 * @author tanul
 * @version 1
 */
public class Samurai extends Player{
    /**
     * Constructor.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Samurai() {
        super( 455);
        addWeaponToInventory(new Uchigatana());
    }

}
