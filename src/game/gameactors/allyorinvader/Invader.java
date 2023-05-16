package game.gameactors.allyorinvader;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.DespawnAction;
import game.gameactors.StatusActor;
import game.gameactors.enemies.Enemy;
import game.reset.ResetManager;
import game.reset.Resettable;

public class Invader extends Enemy implements Resettable {

    private ResetManager rm = ResetManager.getInstance();
    /**
     * Constructor for the Enemy class.
     */
    public Invader(int hitPoints, WeaponItem weapon) {
        super("invader", 'ඞ', hitPoints, 1358, 5578);
        this.addCapability(StatusActor.IS_INVADER);
        addWeaponToInventory(weapon);
        rm.registerResettable(this);
    }

    @Override
    public String reset(GameMap map, boolean rest) {
        if (!rest) {
            DespawnAction despawn = new DespawnAction();
            return despawn.execute(this, map);
        }
        return this + " is not despawned";
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
