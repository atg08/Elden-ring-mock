package game.gameactors.enemies.castle;


import game.weapons.Grossmesser;

/**
 * The GodrickSoldier class represents a soldier enemy named Godrick Soldier
 * that extends the CastleEnemy class.
 *
 * @author Aditti
 * @version 1.0
 * @see CastleEnemy
 */
public class GodrickSoldier extends CastleEnemy {
    /**
     * Constructor for the Enemy class.
     */
    public GodrickSoldier() {
        super("Godrick Soldier", 'p', 198, 38, 70);
        this.addWeaponToInventory(new Grossmesser());
    }

}
