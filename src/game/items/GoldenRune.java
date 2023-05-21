package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.*;
import game.gameactors.StatusActor;
import game.gameactors.players.Player;
import game.utils.RandomNumberGenerator;

import game.utils.RandomNumberGenerator;


/**
 * Represents a Golden Rune, which is a consumable item.
 *
 * @version 1.0
 * @see Item
 * @see Consumable
 */
public class GoldenRune extends Item implements Consumable{


    PlayerRuneManager rm = PlayerRuneManager.getInstance();
    boolean consumed = false;
    final int MAX_AMOUNT = 100000;
    final int MIN_AMOUNT = 200;

    /**
     * Constructor for creating a Golden Rune.
     */
    public GoldenRune() {
        super("Golden Rune", '*', true);
        this.addCapability(ItemUsage.IS_GOLDEN_RUNE);

    }


    /**
     * Checks if the Golden Rune is available for consumption.
     *
     * @return true if the Golden Rune is available, false otherwise
     */
    public boolean isAvailable(){
        return !this.consumed;
    }


    /**
     * Updates the status of the Golden Rune to consumed.
     */
    public void updateStatus(){
        this.consumed = true;
    }


    /**
     * Gets a random amount of Rune from the Golden Rune.
     *
     * @return a Rune object representing the amount obtained from the Golden Rune
     */
    public Rune getRuneAmount(){
        return new Rune(RandomNumberGenerator.getRandomIntInRange(this.MIN_AMOUNT, this.MAX_AMOUNT+1));
    }


    /**
     * Consumes the Golden Rune and increases the player's Rune amount.
     *
     * @param actor the actor who consumes the Golden Rune
     * @return a string indicating the result of the consumption
     */
    @Override
    public String consume(Actor actor) {

        Player player = (Player) actor;
        Rune droppedRune = this.getRuneAmount();
        rm.increaseRune(droppedRune);
        this.updateStatus();

        // remove golden rune from the inventory
        player.removeItemFromInventory(this);
        return "The player consumed Golden Rune and obtained " + droppedRune.getAmount() + " runes";

    }


    /**
     * Determines if the Golden Rune can be consumed by the given actor.
     *
     * @param actor the actor attempting to consume the Golden Rune
     * @return true if the actor is a player and the Golden Rune is available, false otherwise
     */
    @Override
    public Boolean consumeBy(Actor actor) {
        return actor.hasCapability(StatusActor.IS_PLAYER) && this.isAvailable();
    }



}
