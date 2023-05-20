package game.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;


/**
 * The GraftedDragon class represents a weapon item called "Grafted Dragon" that extends the WeaponItem class.
 *
 * @version 1.0
 * @see WeaponItem
 */
public class GraftedDragon extends WeaponItem {
    /**
     * Constructor.
     */
    public GraftedDragon() {
        super("Grafted Dragon", 'N', 89, "bite", 90 );
        this.addCapability(WeaponSkill.TARGETED_ATTACK);
    }
}
