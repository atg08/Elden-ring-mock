package game.gameactors.allyorinvader;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.DespawnAction;
import game.gameactors.StatusActor;
import game.gameactors.players.Player;
import game.reset.ResetManager;
import game.reset.Resettable;

public class Ally extends Actor implements Resettable {

    private ResetManager rm = ResetManager.getInstance();

    /**
     * Constructor.
     */
    public Ally(Player player) {
        super("Ally", 'A', player.getMaxHP());
        this.addCapability(StatusActor.IS_ALLY);
        this.addWeaponToInventory(player.getWeaponInventory().get(0)); // all players have one weapon in inventory
        rm.registerResettable(this);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return null;
    }


    // TODO: 5/14/2023 make it so that not removed on rest but only on players death
    @Override
    public String reset(Actor actor, GameMap map) {
        DespawnAction despawn = new DespawnAction();
        return despawn.execute(this, map);
    }

    @Override
    public boolean isRemovable() {
        return true;
    }

    public boolean isRemovableOnPlayerRest() {
        return false;
    }
}
