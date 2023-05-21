package game.gameactors.allyorinvader;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.DespawnAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;
import game.gameactors.StatusActor;
import game.gameactors.enemies.NPC;
import game.reset.ResetManager;
import game.reset.Resettable;

public class Ally extends NPC implements Resettable {

    private ResetManager rm = ResetManager.getInstance();

    /**
     * Constructor.
     */
    public Ally(int hitPoints, WeaponItem weapon) {
        super("Ally", 'A', hitPoints);
        this.addCapability(StatusActor.IS_ALLY);
        this.addCapability(StatusActor.HOSTILE_TO_ENEMY);
        this.addCapability(StatusActor.IS_DEATH_RUNE_DROPPER);
        this.addWeaponToInventory(weapon); // all players have one weapon in inventory
        rm.registerResettable(this);

        // add  behaviours
        this.behaviours.put(1, new AttackBehaviour(NPC.player));
        this.behaviours.put(3, new WanderBehaviour());
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        return super.playTurn(actions, lastAction, map, display);
    }

    @Override
    public boolean canTarget(Actor subject) {
        return subject.hasCapability(StatusActor.HOSTILE_TO_PLAYER);
    }


    // TODO: 5/14/2023 make it so that not removed on rest but only on players death
    @Override
    public String reset(GameMap map, boolean rest) {
        if (!rest) {
            DespawnAction despawn = new DespawnAction();
            return despawn.execute(this, map);
        }
        return this + " is not despawned";
    }

    @Override
    public boolean isRemovable() {
        return true;
    }

    public boolean isRemovableOnPlayerRest() {
        return false;
    }

}
