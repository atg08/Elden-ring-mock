package game.gameactors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.ExchangeItemToWeaponAction;
import game.actions.PurchaseAction;
import game.actions.SellAction;
import game.gameactors.players.Player;
import game.items.RemembranceOfTheGrafted;
import game.weapons.Sellable;
import game.weapons.WeaponTradingAvailabilityStatus;

public class FingerReaderEnia extends Actor {
    /**
     * Constructor.
     *
     */
    public FingerReaderEnia() {
        super("Finger Reader Enia", 'E', 9999999);

        this.addCapability(StatusActor.IS_TRADER);
        this.addCapability(StatusActor.CANNOT_BE_ATTACKED);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(StatusActor.IS_PLAYER)){
            Player player = (Player) otherActor;
            RemembranceOfTheGrafted remembranceOfTheGrafted = player.getExistingRemembranceOfTheGrafted();
            if (remembranceOfTheGrafted != null){actions.add(new ExchangeItemToWeaponAction(remembranceOfTheGrafted, this));}

            // create SellActions for each sellable weapon
            for (WeaponItem weaponItem: otherActor.getWeaponInventory()){
                if (weaponItem.hasCapability(WeaponTradingAvailabilityStatus.SELLABLE)) {
                    Sellable sellable = (Sellable) weaponItem;
                    if (sellable.isSellableToAnActor(this)){
                        actions.add(new SellAction(weaponItem));
                    }
                }
            }

        }
        return actions;
    }
}
