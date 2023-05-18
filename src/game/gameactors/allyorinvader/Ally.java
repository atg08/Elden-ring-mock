package game.gameactors.allyorinvader;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.DespawnAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;
import game.gameactors.StatusActor;
import game.gameactors.enemies.IFollowable;
import game.gameactors.enemies.NPC;
import game.gameactors.players.Player;
import game.reset.ResetManager;
import game.reset.Resettable;

public class Ally extends NPC implements Resettable, IFollowable {

    private ResetManager rm = ResetManager.getInstance();
    private Location previousLocation;

    /**
     * Constructor.
     */
    public Ally(Player player) {
        super("Ally", 'A', player.getMaxHP());
        this.addCapability(StatusActor.IS_ALLY);
        this.addCapability(StatusActor.HOSTILE_TO_ENEMY);
        this.addCapability(StatusActor.IS_DEATH_RUNE_DROPPER);
        this.addWeaponToInventory(player.getWeaponInventory().get(0)); // all players have one weapon in inventory
        rm.registerResettable(this);

        // add  behaviours
        this.behaviours.put(1, new AttackBehaviour());
        this.behaviours.put(3, new WanderBehaviour());
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // record ally's location
        this.previousLocation = map.locationOf(this);

        return super.playTurn(actions, lastAction, map, display);
    }

    @Override
    public boolean canTarget(Actor subject) {
        return subject.hasCapability(StatusActor.IS_ENEMY);
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

    @Override
    public Location getPlayerPreviousLocation() {
        return this.previousLocation;
    }
}
