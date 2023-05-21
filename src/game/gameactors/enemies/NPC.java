package game.gameactors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.Behaviour;
import game.gameactors.players.Player;

import java.util.Map;
import java.util.TreeMap;


/**
 * The NPC (Non-Player Character) class represents an abstract entity in a game that is controlled by the computer.
 * It extends the Actor class and provides additional functionality specific to NPCs.
 *
 * @author Satoshi
 * @version 1.0
 * @see Actor
 */
public abstract class NPC extends Actor {
    protected static Player player;
    protected Map<Integer, Behaviour> behaviours = new TreeMap<>();

    /**
     * Constructs an NPC object with the specified name, display character, and hit points.
     *
     * @param name        the name of the NPC
     * @param displayChar the character used to represent the NPC in the game display
     * @param hitPoints   the initial hit points of the NPC
     */
    public NPC(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);

    }


    /**
     * Overrides the playTurn method from the Actor class. It determines the action to be taken by the NPC
     * during its turn.
     *
     * @param actions    the list of available actions
     * @param lastAction the last action performed
     * @param map        the game map
     * @param display    the display object for rendering the game
     * @return the action to be performed by the NPC during its turn
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * Determines if the NPC can target the specified actor.
     *
     * @param subject the actor to check if it can be targeted
     * @return true if the NPC can target the actor, false otherwise
     */
    public abstract boolean canTarget(Actor subject);

    /**
     * we need to override this function because the actor class returns the punch intrinsic weapon always, which is not
     * applicable to NPCs and we are not supposed to change the Actor class
     *
     * Overrides the getIntrinsicWeapon method from the Actor class. NPCs don't have an intrinsic weapon,
     * so this method always returns null.
     *
     * @return null (NPCs don't have an intrinsic weapon)
     * @see Actor#getIntrinsicWeapon()
     */

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return null;
    }

    public static void addPlayer(Player player){
        NPC.player = player;
    }

}
