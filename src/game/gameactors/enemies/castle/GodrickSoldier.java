package game.gameactors.enemies.castle;


import game.weapons.Grossmesser;

public class GodrickSoldier extends CastleEnemy {
    /**
     * Constructor for the Enemy class.
     */
    public GodrickSoldier() {
        super("Godrick Soldier", 'p', 198, 38, 70);
        this.addWeaponToInventory(new Grossmesser());
    }

}
