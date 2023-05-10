package edu.monash.fit2099.engine.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.gameactors.players.Player;
import game.runes.Rune;

public class DropRuneAction extends Action {
    private Rune droppedRune;

    /**
     * drops rune at player's previous location
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map){
        Player player = (Player) actor;
        Location playerPreviousLocation = player.getPlayerPreviousLocation();

        this.droppedRune = player.getDeathRune();
        this.droppedRune.setRuneLocation(map.locationOf(player));

        map.locationOf(player).addItem(this.droppedRune);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " dropped " + this.droppedRune.getAmount();
    }
}
