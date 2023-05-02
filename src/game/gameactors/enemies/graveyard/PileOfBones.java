package game.gameactors.enemies.graveyard;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.reset.ResetManager;
import game.reset.Resettable;
import game.actions.DespawnAction;
import game.actions.TurnIntoPileOfBonesAction;
import game.actions.TurnIntoSkeletonAction;
import game.gameactors.enemies.DeathRuneDroppper;
import game.gameactors.enemies.Enemy;
import game.gameactors.players.Player;
import game.runes.Rune;
import game.utils.RandomNumberGenerator;

public class PileOfBones extends Actor implements Resettable, DeathRuneDroppper {

    private final int REVIVE_TO_SKELETON = 3;

    private int numberOfTurnsAlive = 0;

    private Skeleton reviveBackTo;
    public int minRuneDrop;
    public int maxRuneDrop;
    protected ResetManager rm = ResetManager.getInstance();

    /**
     * Constructor
     * @param reviveBackTo
     */
    public PileOfBones(Skeleton reviveBackTo) {
        super("Pile Of Bones", 'X', 1);
        this.minRuneDrop= reviveBackTo.getMinRune();
        this.maxRuneDrop = reviveBackTo.getMaxRune();
        this.reviveBackTo = reviveBackTo;
        rm.registerResettable(this);
    }

    // have it so on death of SKELETON_TYPE GAMEMAP REMOVE ACTOR HSS ADD ACTOR PILE OF BONES
    // RESET DESPAWN 100% PILE OF BONES AND OTHERS

    // counter for numbers of turns not killed to turn back into HSS

    private boolean checkForRevive(){
        this.numberOfTurnsAlive +=1;
        return this.numberOfTurnsAlive == this.REVIVE_TO_SKELETON;
    }

    @Override
    public Rune getDeathRune(){
        return new Rune(RandomNumberGenerator.getRandomIntInRange(this.minRuneDrop, this.maxRuneDrop));
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


    @Override
    public String reset(Actor actor, GameMap map) {
        DespawnAction despawn = new DespawnAction();
        // this is so that enemy that has been removed
        // won't be attempted to be removed again
        return despawn.execute(this, map);
    }

    @Override
    public boolean isRemovable() {
        return true;
    }
}
