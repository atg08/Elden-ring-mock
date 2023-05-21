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


/**
 * The ExchangeItemToWeaponAction class represents an action where an actor exchanges an item with a weapon.
 * It extends the Action class.
 *
 * @version 1.0
 * @see Action
 */
public class ExchangeItemToWeaponAction extends Action{
    Random rand = new Random();
    Item item;
    Actor trader;


    /**
     * Constructs an ExchangeItemToWeaponAction object with the specified item and trader.
     *
     * @param item   the item to be exchanged
     * @param trader the trader actor involved in the exchange
     */
    public ExchangeItemToWeaponAction(Item item, Actor trader){
        this.item = item;
        this.trader = trader;
    }

    /**
     * Executes the exchange item to weapon action.
     *
     * @param actor the actor performing the action
     * @param map   the game map
     * @return a string indicating the result of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        // remove item
        actor.removeItemFromInventory(this.item);

        // add new item to the inventory
        WeaponItem exchangedWeapon = this.getWeaponForExchange();
        actor.addWeaponToInventory(exchangedWeapon);

        return "Tarnished exchanged" + this.item.toString() + " to " + exchangedWeapon.toString();

    }


    /**
     * Retrieves a weapon item for the exchange from the available options.
     *
     * @return a weapon item for the exchange
     * @see Exchangeable
     */
    private WeaponItem getWeaponForExchange(){
        Exchangeable exchangeableItem = (Exchangeable) this.item;
        List<WeaponItem> availableWeaponsForExchange = exchangeableItem.getAvailableWeaponsForExchange();
        int itemIdx = rand.nextInt(availableWeaponsForExchange.size());
        return availableWeaponsForExchange.get(itemIdx);
    }


    /**
     * Returns the menu description of the exchange action.
     *
     * @param actor the actor performing the action
     * @return a string describing the exchange action
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Tarnished exchanges " + this.item + " with " + this.trader;
    }
}
