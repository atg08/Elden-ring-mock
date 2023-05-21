package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.gameactors.players.Player;
import game.items.GoldenRune;
import game.items.Rune;
import game.items.PlayerRuneManager;

public class GoldenRuneConsumeAction extends Action {

    PlayerRuneManager rm = PlayerRuneManager.getInstance();
    GoldenRune goldenRune;

    public GoldenRuneConsumeAction(GoldenRune goldenRune){
        this.goldenRune = goldenRune;
    }

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

    @Override
    public String menuDescription(Actor actor) {
        return "Consume Golden Rune";
    }
}
