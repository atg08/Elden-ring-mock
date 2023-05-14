package game.items;

import edu.monash.fit2099.engine.weapons.WeaponItem;

import java.util.List;

public interface Exchangeable {
    List<WeaponItem> getAvailableWeaponsForExchange();
}
