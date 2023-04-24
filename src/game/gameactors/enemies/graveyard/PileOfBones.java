package game.gameactors.enemies.graveyard;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.DespawnAction;

public class PileOfBones extends Skeleton{
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public PileOfBones() {
        super("Pile Of Bones", 'X', 1);
    }

    // have it so on death of SKELETON_TYPE GAMEMAP REMOVE ACTOR HSS ADD ACTOR PILE OF BONES
    // RESET DESPAWN 100% PILE OF BONES AND OTHERS

}
