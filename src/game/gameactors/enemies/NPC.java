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

public abstract class NPC extends Actor {
    protected static Player player;
    protected Map<Integer, Behaviour> behaviours = new TreeMap<>();

    public NPC(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);

    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;
        }
        return new DoNothingAction();
    }

    public abstract boolean canTarget(Actor subject);

    /**
     * we need to override this function because the actor class returns the punch intrinsic weapon always, which is not
     * applicable to NPCs and we are not supposed to change the Actor class
     * @return
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return null;
    }

    public static void addPlayer(Player player){
        NPC.player = player;
    }

}
