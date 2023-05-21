package game.gameactors.allyorinvader;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.actions.DespawnAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.gameactors.PlayerFollowingManager;
import game.gameactors.StatusActor;
import game.gameactors.enemies.DeathRuneDroppper;
import game.gameactors.enemies.NPC;
import game.items.Rune;
import game.reset.ResetManager;
import game.reset.Resettable;
import game.utils.RandomNumberGenerator;
import game.weapons.WeaponSkill;

import java.util.List;

public class Invader extends NPC implements Resettable, DeathRuneDroppper {
    private final int minDeathRuneAmount = 1358;
    private final int maxDeathRuneAmount = 5578;

    private ResetManager rm = ResetManager.getInstance();
    private final PlayerFollowingManager playerFollowingManager;

    /**
     * Constructor for the Enemy class.
     */
    public Invader(int hitPoints, WeaponItem weapon) {
        super("invader", 'ඞ', hitPoints);
        this.addCapability(StatusActor.HOSTILE_TO_PLAYER);
        this.addCapability(StatusActor.IS_DEATH_RUNE_DROPPER);
        this.addCapability(StatusActor.IS_INVADER);
        this.addWeaponToInventory(weapon);

        this.behaviours.put(1, new AttackBehaviour(NPC.player));
        this.behaviours.put(3, new WanderBehaviour());

        this.playerFollowingManager = new PlayerFollowingManager(NPC.player);


    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display){
        this.playerFollowingManager.updateFollowingStatusIfNeeded(this, map);

        return super.playTurn(actions, lastAction, map, display);
    }

    @Override
    public boolean canTarget(Actor subject) {
        return !subject.hasCapability(StatusActor.IS_INVADER);
    }

    @Override
    public Rune getDeathRune() {
        return new Rune(RandomNumberGenerator.getRandomIntInRange(this.minDeathRuneAmount, this.maxDeathRuneAmount));
    }

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

    @Override
    public boolean isRemovableOnPlayerRest() {
        return false;
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions =  new ActionList();

        if (otherActor.hasCapability(StatusActor.IS_PLAYER)){

            // for intrinsic weapon - assumption: player's intrinsic weapon can only use targeted action
            actions.add(new AttackAction(this, direction));

            // for regular weapons
            for (WeaponItem weaponItem: otherActor.getWeaponInventory()){
                // targeted attack
                if (weaponItem.hasCapability(WeaponSkill.TARGETED_ATTACK)){
                    actions.add(new AttackAction(this, direction, weaponItem));
                }

                // special attack
                Action specialAttack = weaponItem.getSkill(this, direction);
                if (specialAttack != null){
                    actions.add(specialAttack);
                }
            }
        }

        return actions;
    }
}

