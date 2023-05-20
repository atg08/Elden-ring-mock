package game.gameactors.enemies.castle;

import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.DespawnAction;
import game.gameactors.EnemyType;
import game.gameactors.StatusActor;
import game.gameactors.enemies.Enemy;
import game.reset.Resettable;

public abstract class CastleEnemy extends Enemy implements Resettable {
    /**
     * Constructor for the Enemy class.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     * @param minRuneDrop the minimum number of runes this Enemy drops upon death
     * @param maxRuneDrop the maximum number of runes this Enemy drops upon death
     */
    public CastleEnemy(String name, char displayChar, int hitPoints, int minRuneDrop, int maxRuneDrop) {
        super(displayChar, hitPoints, minRuneDrop, maxRuneDrop, name);
        this.addCapability(EnemyType.CASTLE_TYPE);
        rm.registerResettable(this);
        this.addCapability(StatusActor.CAN_DESPAWN);
    }


    @Override
    public boolean isRemovable() {
        return true;
    }

    @Override
    public String reset(GameMap map, boolean rest) {
        DespawnAction despawn = new DespawnAction();
        return despawn.execute(this, map);
    }

    public boolean isRemovableOnPlayerRest() {
        return true;
    }
}
