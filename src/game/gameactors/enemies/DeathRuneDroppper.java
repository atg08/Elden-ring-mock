package game.gameactors.enemies;

import game.items.Rune;

/**
 * The DeathRuneDropper interface represents an enemy that drops a death rune when defeated.
 *
 * @author Tanul , Satoshi , Aditti
 * @version 1.0.0
 */
public interface DeathRuneDroppper {

    /**
     * Returns the death rune dropped by the enemy.
     *
     * @return the death rune dropped by the enemy (by calling the getDeathRune method)
     */
    public Rune getDeathRune();
}
