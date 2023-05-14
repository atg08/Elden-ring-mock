package edu.monash.fit2099.engine.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.gameactors.players.Player;
import game.items.Rune;

/**
 * Action to allow an actor to pick up previously dropped runes.
 * @author Satoshi Kashima
 */
public class RecoverRuneAction extends PickUpAction {
    /**
     * the rune to be recovered
     */
    private final Rune rune;

    /**
     * Constructor. Requires rune as input.
     *
     * @param rune the rune to pick up
     */
    public RecoverRuneAction(Rune rune) {
        super(rune);
        this.rune = rune;
    }

    /**
     * Executes the recover rune action. It picks up the rune from the ground and increases the player's runes.
     * Uses (extends) PickUpAction internally.
     *
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
