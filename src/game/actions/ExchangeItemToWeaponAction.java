package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.items.Exchangeable;

import java.io.WriteAbortedException;
import java.util.List;
import java.util.Random;

public class ExchangeItemToWeaponAction extends Action{
    Random rand = new Random();
    Item item;
    Actor trader;

    public ExchangeItemToWeaponAction(Item item, Actor trader){
        this.item = item;
        this.trader = trader;
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        // remove item
        actor.removeItemFromInventory(this.item);

        // add new item to the inventory
        WeaponItem exchangedWeapon = this.getWeaponForExchange();
        actor.addWeaponToInventory(exchangedWeapon);

        return "Tarnished exchanged" + this.item.toString() + " to " + exchangedWeapon.toString();

    }

    private WeaponItem getWeaponForExchange(){
        Exchangeable exchangeableItem = (Exchangeable) this.item;
        List<WeaponItem> availableWeaponsForExchange = exchangeableItem.getAvailableWeaponsForExchange();
        int itemIdx = rand.nextInt(availableWeaponsForExchange.size());
        return availableWeaponsForExchange.get(itemIdx);
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Tarnished exchanges " + this.item + " with " + this.trader;
    }
}
