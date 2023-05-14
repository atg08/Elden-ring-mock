package game.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;

public class GraftedDragon extends WeaponItem {
    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     */
    public GraftedDragon() {
        super("Grafted Dragon", 'N', 89, "bite", 90 );
        this.addCapability(WeaponSkill.TARGETED_ATTACK);
    }
}
