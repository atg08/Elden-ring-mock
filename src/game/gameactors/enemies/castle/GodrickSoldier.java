package game.gameactors.enemies.castle;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.DespawnAction;
import game.gameactors.enemies.Enemy;
import game.reset.Resettable;

public class GodrickSoldier extends Enemy implements Resettable {
    /**
     * Constructor for the Enemy class.
     */
    public GodrickSoldier() {
        super("Godrick Soldier", 'p', 198, 38, 70);
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
}
