package game.gameactors.enemies.graveyard;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.gameactors.StatusActor;
import game.reset.ResetManager;
import game.reset.Resettable;
import game.actions.DespawnAction;
import game.actions.TurnIntoSkeletonAction;
import game.gameactors.enemies.DeathRuneDroppper;
import game.items.Rune;
import game.utils.RandomNumberGenerator;


/**
 * A class that represents a pile of bones on a game map that can revive
 * back into a {@link Skeleton} after a certain number of turns alive.
 *
 * @author Tanul , Satoshi , Aditti
 * @version 1.0.0
 */
public class PileOfBones extends Actor implements Resettable, DeathRuneDroppper {

    private final int REVIVE_TO_SKELETON = 3;

    private int numberOfTurnsAlive = 0;

    private Skeleton reviveBackTo;
    public int minRuneDrop;
    public int maxRuneDrop;
    protected ResetManager rm = ResetManager.getInstance();

    /**
     * Constructor for creating a new PileOfBones.
     *
     * @param reviveBackTo the {@see Skeleton} instance that this pile of bones can revive to.
     */
    public PileOfBones(Skeleton reviveBackTo) {
        super("Pile Of Bones", 'X', 1);
        this.minRuneDrop= reviveBackTo.getMinRune();
        this.maxRuneDrop = reviveBackTo.getMaxRune();
        this.reviveBackTo = reviveBackTo;
        rm.registerResettable(this);
        this.addCapability(StatusActor.IS_ENEMY);
    }


    /**
     * Checks whether the PileOfBones has been alive for a certain number of turns
     * and can be revived back into a {@see Skeleton}.
     *
     * @return true if the PileOfBones has been alive for a certain number of turns and can be revived back into a Skeleton, false otherwise.
     */
    private boolean checkForRevive(){
        this.numberOfTurnsAlive +=1;
        return this.numberOfTurnsAlive == this.REVIVE_TO_SKELETON;
    }


    /**
     * Returns the {@see Rune} that should be dropped by the PileOfBones when it dies.
     *
     * @return a new instance of {@see Rune} that should be dropped by the PileOfBones when it dies.
     */
    @Override
    public Rune getDeathRune(){
        return new Rune(RandomNumberGenerator.getRandomIntInRange(this.minRuneDrop, this.maxRuneDrop));
    }


    /**
     * Returns the {@see Skeleton} instance that this pile of bones can revive back to.
     *
     * @return the {@see Skeleton} instance that this pile of bones can revive back to.
     */
    public Skeleton getReviveBackTo(){
        return this.reviveBackTo;
    }


    /**
     * Overrides the playTurn method of {@see Actor} to make the PileOfBones revive back into a {@see Skeleton} after
     * a certain number of turns or do nothing otherwise.
     *
     * @param actions    a list of available actions for the PileOfBones.
     * @param lastAction the last Action performed.
     * @param map        the GameMap containing the PileOfBones.
     * @param display    the Display that will display the map.
     * @return a new instance of {@see Action}
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        if (checkForRevive()){
            return new TurnIntoSkeletonAction();
        }

        return new DoNothingAction();
    }



    /**
     * Resets the current instance of Enemy by despawning it from the GameMap.
     *
     * @param map the GameMap instance where this Enemy is located
     * @return the result of the DespawnAction's execution
     */
    @Override
    public String reset(GameMap map, boolean rest) {
        DespawnAction despawn = new DespawnAction();
        // this is so that enemy that has been removed
        // won't be attempted to be removed again
        return despawn.execute(this, map);
    }


    /**
     * Indicates whether the current instance of Enemy is removable or not.
     *
     * @return true, since Enemy instances can always be removed from the GameMap
     */
    @Override
    public boolean isRemovable() {
        return true;
    }

    @Override
    public boolean isRemovableOnPlayerRest() {
        return true;
    }
}
