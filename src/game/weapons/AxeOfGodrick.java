package game.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;

public class AxeOfGodrick extends WeaponItem {
    /**
     * Constructor.
     *
     */
    public AxeOfGodrick() {
        super("Axe of Godrick", 'T', 142, "chop", 84);
        this.addCapability(WeaponSkill.TARGETED_ATTACK);
    }
}
