package game.gameactors.enemies.graveyard;

import game.gameactors.EnemyType;
import game.gameactors.enemies.Enemy;
import game.gameactors.enemies.Revivable;

public class Skeleton extends Enemy implements Revivable {
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     * @param minRuneDrop
     * @param maxRuneDrop
     */
    public Skeleton(String name, char displayChar, int hitPoints, int minRuneDrop, int maxRuneDrop) {
        super(name, displayChar, hitPoints, minRuneDrop, maxRuneDrop);
        this.addCapability(EnemyType.SKELETON_TYPE);
    }

    @Override
    public Revivable revive() {
        return null;
    }
}
