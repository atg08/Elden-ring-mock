package game.gameactors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.PurchaseAction;
import game.actions.SellAction;
import game.weapons.Club;
import game.weapons.GreatKnife;
import game.weapons.Scimitar;
import game.weapons.Uchigatana;

import java.util.ArrayList;

public class Trader extends Actor {

    private ArrayList<WeaponItem> sellableWeaponItems = new ArrayList<>();

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     * @author Tanul
     */
    public Trader(String name, char displayChar, int hitPoints) {
        super("Merchant kale", 'k', 9999999);
        sellableWeaponItems.add(new Uchigatana());
        sellableWeaponItems.add(new GreatKnife());
        sellableWeaponItems.add(new Club());
        sellableWeaponItems.add(new Scimitar());
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // trader does not do make any actions actively. it responds if player requires him
        return null;
    }

    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(StatusActor.IS_ENEMY)){

            // create SellActions for each sellable weapons
            for (WeaponItem weaponItem: otherActor.getWeaponInventory()){
                actions.add(new SellAction(weaponItem));
            }

            // create PurchaseAction for each purchasable weapons
            for (WeaponItem weaponItem: this.sellableWeaponItems){
                actions.add(new PurchaseAction(weaponItem));
            }
        }

        return actions;
    }


}
