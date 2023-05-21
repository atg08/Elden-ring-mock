package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.gameactors.players.Player;
import game.items.GoldenRune;
import game.items.Rune;
import game.items.PlayerRuneManager;


/**
 * Represents an action to consume a Golden Rune.
 *
 * @version 1.0
 * @see Action
 */
public class GoldenRuneConsumeAction extends Action {

    PlayerRuneManager rm = PlayerRuneManager.getInstance();
    GoldenRune goldenRune;


    /**
     * Constructs a GoldenRuneConsumeAction object.
     *
     * @param goldenRune the Golden Rune to be consumed
     */
    public GoldenRuneConsumeAction(GoldenRune goldenRune){
        this.goldenRune = goldenRune;
    }


    /**
     * Executes the action of consuming a Golden Rune.
     *
     * @param actor the Actor performing the action (should be a Player)
     * @param map   the GameMap where the action is being executed
     * @return a message indicating the result of the consumption
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;

        if (this.goldenRune.isAvailable()) {
            Rune droppedRune = this.goldenRune.getRuneAmount();
            rm.increaseRune(droppedRune);
            this.goldenRune.updateStatus();

            // remove golden rune from the inventory
            player.removeItemFromInventory(this.goldenRune);
            return "The player consumed Golden Rune and obtained " + droppedRune.getAmount() + " runes";
        }

        return "The golden rune is already consumed";
    }

    /**
     * Returns the menu description for the action.
     *
     * @param actor the Actor for whom the menu description is generated
     * @return the menu description
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Consume Golden Rune";
    }
}
