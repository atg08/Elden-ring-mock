package game.gameactors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.ExchangeItemToWeaponAction;
import game.actions.PurchaseAction;
import game.actions.SellAction;
import game.gameactors.players.Player;
import game.items.RemembranceOfTheGrafted;

public class FingerReaderEnia extends Actor {
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public FingerReaderEnia(String name, char displayChar, int hitPoints) {
        super("Finger Reader Enia", 'E', 9999999);

        this.addCapability(StatusActor.IS_TRADER);
        this.addCapability(StatusActor.CANNOT_BE_ATTACKED);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return null;
    }

    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(StatusActor.IS_PLAYER)){
            Player player = (Player) otherActor;
            RemembranceOfTheGrafted remembranceOfTheGrafted = player.getExistingRemembranceOfTheGrafted();
            actions.add(new ExchangeItemToWeaponAction(remembranceOfTheGrafted, this));
        }
        return actions;
    }
}
