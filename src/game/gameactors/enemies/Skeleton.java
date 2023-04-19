package game.gameactors.enemies;

public abstract class Skeleton extends Enemy{

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Skeleton(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }

    // add PileOfBones
}
