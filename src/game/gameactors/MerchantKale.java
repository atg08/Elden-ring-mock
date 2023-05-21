package game.gameactors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.PurchaseAction;
import game.actions.SellAction;
import game.weapons.WeaponTradingAvailabilityStatus;
import game.weapons.*;

import java.util.ArrayList;

/**
 * The MerchantKale class represents a merchant character named Merchant Kale in a game.
 * It extends the Actor class.
 *
 * @author Tanul
 * @version 1.0
 * @see Actor
 */
public class MerchantKale extends Actor {

    private ArrayList<WeaponItem> sellableWeaponItems = new ArrayList<>();

    /**
     * Constructor for the MerchantKale class.
     */
    public MerchantKale() {
        super("Merchant Kale", 'k', 9999999);
        this.sellableWeaponItems.add(new Uchigatana());
        this.sellableWeaponItems.add(new GreatKnife());
        this.sellableWeaponItems.add(new Club());
        this.sellableWeaponItems.add(new Scimitar());

        this.addCapability(StatusActor.IS_TRADER);
        this.addCapability(StatusActor.CANNOT_BE_ATTACKED);
    }

    /**
     * Plays a turn for the MerchantKale.
     *
     * @param actions    The list of available actions.
     * @param lastAction The last action performed.
     * @param map        The game map.
     * @param display    The display for the game.
     * @return The action to be performed by the MerchantKale.
     * @see Action
     * @see DoNothingAction
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // trader does not do make any actions actively. it responds if player requires him
        return new DoNothingAction();
    }

    /**
     * Restocks the sellableWeaponItems by replacing a weapon with a restocked version.
     *
     * @param weapon The weapon to be restocked.
     */
    public void restock(WeaponItem weapon){
        this.sellableWeaponItems.remove(weapon);
        Purchasable purchasableWeapon = (Purchasable) weapon;
        this.sellableWeaponItems.add(purchasableWeapon.restock());

    }

    /**
     * Returns the list of allowable actions for the merchant.
     *
     * @param otherActor The other actor interacting with the merchant.
     * @param direction  The direction of the interaction.
     * @param map        The game map.
     * @return The list of allowable actions for the merchant.
     * @see Actor
     * @see ActionList
     * @see SellAction
     * @see PurchaseAction
     */
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(StatusActor.IS_PLAYER)){

            // create SellActions for each sellable weapon
            for (WeaponItem weaponItem: otherActor.getWeaponInventory()){
                if (weaponItem.hasCapability(WeaponTradingAvailabilityStatus.SELLABLE)) {
                    Sellable sellable = (Sellable) weaponItem;
                    if (sellable.isSellableToAnActor(this)){
                        actions.add(new SellAction(weaponItem));
                    }
                }
            }

            // create PurchaseAction for each purchasable weapons
            for (WeaponItem weaponItem: this.sellableWeaponItems){
                actions.add(new PurchaseAction(weaponItem, this));
            }
        }

        return actions;
    }


}
