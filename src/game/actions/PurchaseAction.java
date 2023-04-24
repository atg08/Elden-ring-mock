package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.gameactors.players.Player;
import game.weapons.Purchasable;

public class PurchaseAction extends Action {
    private WeaponItem weapon;

    public PurchaseAction(WeaponItem weapon){
        this.weapon = weapon;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String result;
        Player player = (Player) actor;
        Purchasable purchasableWeapon = (Purchasable) this.weapon;
        if (player.decreaseRune(purchasableWeapon.getPurchasingPrice())){
            player.addWeaponToInventory(this.weapon);
            result = "Tarnished purchased " + this.weapon.toString();
        }else{
            result = "Tarnished could not purchase the item. He did not have enough runes.";
        }

        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        Purchasable purchasableWeapon = (Purchasable) this.weapon;
        return "Tarnished purchases " + this.weapon.toString() + " for " + purchasableWeapon.getPurchasingPrice();
    }
}
