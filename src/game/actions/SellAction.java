package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.gameactors.players.Player;
import game.weapons.Sellable;

public class SellAction extends Action {
    private WeaponItem weapon;

    public SellAction(WeaponItem weapon){
        this.weapon = weapon;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        Sellable sellableWeapon = (Sellable) this.weapon;
        player.increaseRune(sellableWeapon.getSellingPrice());
        player.removeWeaponFromInventory(this.weapon);
        return  "Tarnished sold " + this.weapon.toString();
    }

    @Override
    public String menuDescription(Actor actor) {
        Sellable sellableWeapon = (Sellable) this.weapon;
        return "Tarnished sells " + this.weapon.toString() + " for " + sellableWeapon.getSellingPrice().getAmount();
    }
}
