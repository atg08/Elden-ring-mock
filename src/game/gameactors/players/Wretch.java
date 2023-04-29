package game.gameactors.players;

import game.weapons.Club;

public class Wretch extends Player{
    /**
     * Constructor.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Wretch(String name, char displayChar, int hitPoints) {
        super( 414);
        addWeaponToInventory(new Club());
    }
}
