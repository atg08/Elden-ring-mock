package game.gameactors.players;

import game.weapons.Club;
/**

 This is the concrete class for Wretch players
 @author tanul, aditti, satoshi
 @version 1
 */
public class Wretch extends Player{
    /**

     Constructor for the Wretch class.
     It sets the initial hitpoints of the Wretch to 414 and adds a Club weapon to the inventory.
     */
    public Wretch() {
        super( 414);
        addWeaponToInventory(new Club());
    }
}
