package game.gameactors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.ExchangeItemToWeaponAction;
import game.actions.SellAction;
import game.gameactors.players.Player;
import game.items.RemembranceOfTheGrafted;
import game.weapons.Sellable;
import game.weapons.WeaponTradingAvailabilityStatus;

/**
 * Represents a Finger Reader Enia, a type of Actor in the game.
 *
 * @author Tanul, Satoshi, Aditti
 * @version 1.0
 * @see Actor
 */
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

    /**
     * Plays a turn for the FingerReaderEnia.
     *
     * @param actions    the list of available actions for the FingerReaderEnia
     * @param lastAction the last action performed
     * @param map        the GameMap object representing the game map
     * @param display    the Display object used to display the game
     * @return the Action to be performed by the FingerReaderEnia
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * Returns a list of allowable actions for the FingerReaderEnia towards another Actor.
     *
     * @param otherActor the other Actor involved in the interaction
     * @param direction  the direction of the interaction
     * @param map        the GameMap object representing the game map
     * @return an ActionList containing allowable actions for the FingerReaderEnia
     * @see ActionList
     */
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
