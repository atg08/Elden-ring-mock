package game.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;


/**
 * The AxeOfGodrick class represents an axe item called "Axe of Godrick" that extends the WeaponItem class.
 *
 * @version 1.0
 * @see WeaponItem
 */
public class AxeOfGodrick extends WeaponItem {


    /**
     * Constructs a new AxeOfGodrick object.
     * The axe has the name "Axe of Godrick" and a symbol 'T'.
     * It has 142 damage points and has a damage type of "chop" with a damage value of 84.
     * It is capable of performing targeted attacks.
     */
    public AxeOfGodrick() {
        super("Axe of Godrick", 'T', 142, "chop", 84);
        this.addCapability(WeaponSkill.TARGETED_ATTACK);
    }
}
