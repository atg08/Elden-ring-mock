package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.environments.siteoflostgrace.SiteOfLostGrace;
import game.gameactors.players.Player;
import game.reset.ResetManager;


/**
 * Represents an action where an actor rests at a site of lost grace.
 * @author tanul
 * @version 1
 */
public class RestAction extends Action {

    private SiteOfLostGrace site;

    public RestAction(SiteOfLostGrace _site) {
        this.site = _site;
    }

    /**
     * The ResetManager instance
     */
    private ResetManager rm = ResetManager.getInstance();

    /**
     * Calls the ResetManager to perform the reset of the game.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A string describing the result of the action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        rm.run(map, true); // this is because actor is resting
        Player player = (Player) actor;
//            player.setRespawnPoint(this);
        player.setRespawnLocation(this.site.getSiteLocation());
        return actor + " has rested at a site of lost grace";
    }

    /**
     * Returns a string describing the rest action.
     *
     * @param actor The actor performing the action.
     * @return A string describing the rest action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " can rest";
    }
}