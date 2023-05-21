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

public class MerchantKale extends Actor {

    private ArrayList<WeaponItem> sellableWeaponItems = new ArrayList<>();

    /**
     * Constructor.
     ** @author Tanul
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

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // trader does not do make any actions actively. it responds if player requires him
        return new DoNothingAction();
    }

    public void restock(WeaponItem weapon){
        this.sellableWeaponItems.remove(weapon);
        Purchasable purchasableWeapon = (Purchasable) weapon;
        this.sellableWeaponItems.add(purchasableWeapon.restock());

    }

    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(StatusActor.IS_PLAYER)){

            // create SellActions for each sellable weapon
            for (WeaponItem weaponItem: otherActor.getWeaponInventory()){
                if (weaponItem.hasCapability(WeaponTradingAvailabilityStatus.SELLABLE)) {
                    actions.add(new SellAction(weaponItem));
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