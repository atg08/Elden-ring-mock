package game.gameactors.players;

import game.weapons.Uchigatana;

/**

 * This is the concrete class for Samurai
 * @author tanul, aditti, satoshi
 * @version 1.0
 * @see Player
 */
public class Samurai extends Player{
    /**

     Constructor for the Samurai class.
     It sets the initial hitpoints of the Samurai to 455 and adds a Uchigatana weapon to the inventory.
     */
    public Samurai() {
        super( 455);
        addWeaponToInventory(new Uchigatana());
        this.originalWeapon = new Uchigatana();
    }

}
