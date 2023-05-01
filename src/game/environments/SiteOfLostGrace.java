package game.environments;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.RestAction;
import game.gameactors.StatusActor;
import game.gameactors.players.Player;

public abstract class SiteOfLostGrace extends Ground {


    protected static Location siteLocation;


    /**
     * Constructor.
     */
    public SiteOfLostGrace() {
        super('U');
    }

    public Location getSiteLocation() {
        return this.siteLocation;
    }

    @Override
    public void tick(Location location) {
//        super.tick(location);
        this.siteLocation = location;
    }

    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = new ActionList();
        if (actor.hasCapability(StatusActor.CAN_REST)){
            actions.add(new RestAction());

            // in future when we rest at a site of lost grace we want it to
            // become new respawn point
            Player player = (Player) actor;
            player.setRespawnPoint(this);
        }
        return actions;
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(StatusActor.IS_PLAYER);
    }
}
