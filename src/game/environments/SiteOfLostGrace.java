package game.environments;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ActivateAction;
import game.actions.RestAction;
import game.gameactors.StatusActor;
import game.gameactors.players.Player;


/**

 A class representing a site of lost grace, a type of ground where a player can rest and set a new respawn point.
 @author tanul
 */
public abstract class SiteOfLostGrace extends Ground {


    protected Boolean isActivated = false;

    protected Boolean isDiscovered = false;

    public Boolean getDiscovered() {
        return isDiscovered;
    }

    public void setDiscovered(Boolean discovered) {
        isDiscovered = discovered;
    }

    public Boolean getActivated() {
        return isActivated;
    }

    public void setActivated(Boolean activated) {
        isActivated = activated;
    }

    /**

     The location of the site.
     */
    protected Location siteLocation;


    /**
     * Constructor.
     */
    public SiteOfLostGrace() {
        super('U');
    }

    /**
     Gets the location of the site.
     @return the location of the site.
     */
    public Location getSiteLocation() {
        return this.siteLocation;
    }

    /**
     Sets the location of the site. At each tick
     @param location the location of the site.
     */
    @Override
    public void tick(Location location) {
//        super.tick(location);
        this.siteLocation = location.map().at(location.x(),location.y());
    }

    /**
     Gets a list of allowable actions that can be performed at the site.

     @param actor The actor performing the action.

     @param location The location of the actor.

     @param direction The direction of the action.

     @return A list of allowable actions that can be performed at the site.
     */
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = new ActionList();
        if (actor.hasCapability(StatusActor.CAN_REST)){
            if (this.getActivated()){
            actions.add(new RestAction(this));

            // in future when we rest at a site of lost grace we want it to
            // become new respawn point

        }
            else {
                if (this.getDiscovered() == false) {
                    for (String line : ActivateAction.LOST_GRACE_DISCOVERED.split("\n")) {
                        new Display().println(line);
                        try {
                            Thread.sleep(200);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                    this.setDiscovered(true);
                }
                actions.add(new ActivateAction(this));
            }

    }
        return actions;
    }

    /**

     Determines if an actor can enter the site.
     @param actor The actor trying to enter.
     @return True if the actor can enter, false otherwise.
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(StatusActor.IS_PLAYER);
    }
}
