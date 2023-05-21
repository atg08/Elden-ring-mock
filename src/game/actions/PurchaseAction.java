package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.gameactors.MerchantKale;
import game.gameactors.players.Player;
import game.items.PlayerRuneManager;
import game.weapons.Purchasable;

/**
 * Action for a player to purchase a weapon from a trader.
 * @author Satoshi Kashima
 */
public class PurchaseAction extends Action {
    /**
     * The weapon to be sold
     */
    private WeaponItem weapon;

    /**
     * the trader
     */
    private MerchantKale trader;

    PlayerRuneManager rm = PlayerRuneManager.getInstance();


    /**
     * Constructor for PurchaseAction.
     *
     * @param weapon The weapon to be purchased.
     * @param trader The trader selling the weapon.
     */
    public PurchaseAction(WeaponItem weapon, MerchantKale trader) {
        this.weapon = weapon;
        this.trader = trader;
    }

    /**
     * Executes the purchase action.
     * Returns unsuccessful message if there isn't enough rune for the player to buy the given weapon.
     * It ensures that the purchased weapon object is no longer stored in trader's weapon lists.
     *
     * @param actor The actor performing the action.
     * @param map The map on which the action is performed.
     * @return A message describing the result of the action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result;
        Player player = (Player) actor;
        Purchasable purchasableWeapon = (Purchasable) this.weapon;
        if (rm.decreaseRune(purchasableWeapon.getPurchasingPrice())){
            player.addWeaponToInventory(this.weapon);
            trader.restock(this.weapon);
            result = "Tarnished purchased " + this.weapon.toString();
        }else{
            result = "Tarnished could not purchase the item. He did not have enough runes.";
        }

        return result;
    }

    /**
     * Describes what weapon was purchased for what amount of runes.
     *
     * @param actor The actor performing the action.
     * @return A string description of the action.
     */
    @Override
    public String menuDescription(Actor actor) {
        Purchasable purchasableWeapon = (Purchasable) this.weapon;
        return "Tarnished purchases " + this.weapon.toString() + " for " + purchasableWeapon.getPurchasingPrice().getAmount();
    }
}
