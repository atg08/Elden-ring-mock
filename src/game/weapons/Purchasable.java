package game.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.runes.Rune;

public interface Purchasable {
    public Rune getPurchasingPrice();

    public WeaponItem restock();
}
