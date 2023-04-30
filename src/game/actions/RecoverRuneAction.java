package edu.monash.fit2099.engine.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.gameactors.players.Player;
import game.runes.Rune;

/**
 * Action to allow items to be picked up.
 */
public class RecoverRuneAction extends PickUpAction {

    private final Rune rune;

    /**
     * Constructor.
     *
     * @param rune the rune to pick up
     */
    public RecoverRuneAction(Rune rune) {
        super(rune);
        this.rune = rune;
    }

    /**
     * Add the item to the actor's inventory.
     *
     * @see Action#execute(Actor, GameMap)
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a suitable description to display in the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        player.increaseRune(this.rune);

        super.execute(actor, map);

        return "Player picks up " + this.rune.getAmount() + " runes";
    }
}
