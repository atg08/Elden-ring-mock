package game.gameactors.enemies.castle;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.DespawnAction;
import game.gameactors.enemies.Enemy;
import game.reset.Resettable;

public class Dog extends Enemy implements Resettable {
    /**
     * Constructor for the Enemy class.
     */
    public Dog() {
        super("Dog", 'a', 104, 52, 1390);
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(101, "bites", 93);
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
