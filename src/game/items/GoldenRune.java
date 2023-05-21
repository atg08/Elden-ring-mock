package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.*;
import game.actions.ConsumeAction;
import game.gameactors.StatusActor;
import game.gameactors.players.Player;
import game.utils.RandomNumberGenerator;

import game.utils.RandomNumberGenerator;

public class GoldenRune extends Item implements Consumable{


    RuneManager rm = RuneManager.getInstance();
    boolean consumed = false;
    final int MAX_AMOUNT = 100000;
    final int MIN_AMOUNT = 200;

    /***
     * Constructor.
     */
    public GoldenRune() {
        super("Golden Rune", '*', true);
        this.addCapability(ItemUsage.IS_GOLDEN_RUNE);

    }

    public boolean isAvailable(){
        return !this.consumed;
    }

    public void updateStatus(){
        this.consumed = true;
    }

    public Rune getRuneAmount(){
        return new Rune(RandomNumberGenerator.getRandomIntInRange(this.MIN_AMOUNT, this.MAX_AMOUNT+1));
    }

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

    @Override
    public Boolean consumeBy(Actor actor) {
        return actor.hasCapability(StatusActor.IS_PLAYER) && this.isAvailable();
    }



}
