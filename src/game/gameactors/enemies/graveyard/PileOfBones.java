package game.gameactors.enemies.graveyard;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.DespawnAction;
import game.actions.TurnIntoPileOfBonesAction;
import game.actions.TurnIntoSkeletonAction;
import game.gameactors.enemies.Enemy;

public class PileOfBones extends Enemy {

    private final int REVIVE_TO_SKELETON = 3;

    private int numberOfTurnsAlive = 0;

    private Skeleton reviveBackTo;

    /**
     * Constructor
     * @param reviveBackTo
     */
    public PileOfBones(Skeleton reviveBackTo) {
        super("Pile Of Bones", 'X', 1, reviveBackTo.getMinRune(), reviveBackTo.getMaxRune());
        this.reviveBackTo = reviveBackTo;
    }

    // have it so on death of SKELETON_TYPE GAMEMAP REMOVE ACTOR HSS ADD ACTOR PILE OF BONES
    // RESET DESPAWN 100% PILE OF BONES AND OTHERS

    // counter for numbers of turns not killed to turn back into HSS

    private boolean checkForRevive(){
        this.numberOfTurnsAlive +=1;
        return this.numberOfTurnsAlive == this.REVIVE_TO_SKELETON;
    }

    public Skeleton getReviveBackTo(){
        return this.reviveBackTo;
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        if (checkForRevive()){
            return new TurnIntoSkeletonAction();
        }

        return new DoNothingAction();
    }



}
