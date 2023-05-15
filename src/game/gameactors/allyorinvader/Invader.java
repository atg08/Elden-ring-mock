package game.gameactors.allyorinvader;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.DespawnAction;
import game.gameactors.StatusActor;
import game.gameactors.enemies.Enemy;
import game.gameactors.players.Player;
import game.reset.ResetManager;
import game.reset.Resettable;

public class Invader extends Enemy implements Resettable {

    private ResetManager rm = ResetManager.getInstance();
    /**
     * Constructor for the Enemy class.
     */
    public Invader(Player player) {
        super("invader", 'ඞ', player.getMaxHP(), 1358, 5578);
        this.addCapability(StatusActor.IS_INVADER);
        addWeaponToInventory(player.getWeaponInventory().get(0));
        rm.registerResettable(this);
    }

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
    public boolean isRemovableOnPlayerRest() {
        return false;
    }
}
