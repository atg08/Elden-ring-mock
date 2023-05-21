package game.gameactors.allyorinvader;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.actions.DespawnAction;
import game.behaviours.AttackBehaviour;
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


/**
 * The Invader class represents an invader character in a game. It extends the NPC class and implements the Resettable,
 * DeathRuneDroppper.
 *
 * @version 1.0
 * @see NPC
 * @see Resettable
 * @see DeathRuneDroppper
 */
public class Invader extends NPC implements Resettable, DeathRuneDroppper {
    private final int minDeathRuneAmount = 1358;
    private final int maxDeathRuneAmount = 5578;

    private ResetManager rm = ResetManager.getInstance();
    private final PlayerFollowingManager playerFollowingManager;

    /**
     * Constructor for the Invader class.
     *
     * @param hitPoints the hit points of the invader.
     * @param weapon the weapon item of the invader.
     */
    public Invader(int hitPoints, WeaponItem weapon) {
        super("invader", 'ඞ', hitPoints);
        this.addCapability(StatusActor.HOSTILE_TO_PLAYER);
        this.addCapability(StatusActor.IS_DEATH_RUNE_DROPPER);
        this.addCapability(StatusActor.IS_INVADER);
        this.addWeaponToInventory(weapon);

        this.behaviours.put(1, new AttackBehaviour(NPC.player));
        this.behaviours.put(1, new FollowBehaviour(NPC.player));
        this.behaviours.put(3, new WanderBehaviour());

        this.playerFollowingManager = new PlayerFollowingManager(NPC.player);


    }


    /**
     * Plays a turn for the invader.
     *
     * @param actions the list of available actions.
     * @param lastAction the last action performed.
     * @param map the game map.
     * @param display the display used for rendering.
     * @return the action to be performed.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display){
        this.playerFollowingManager.updateFollowingStatusIfNeeded(this, map);

        return super.playTurn(actions, lastAction, map, display);
    }


    /**
     * Checks if the invader can target a given actor.
     *
     * @param subject the actor to be targeted.
     * @return true if the invader can target the actor, false otherwise.
     */
    @Override
    public boolean canTarget(Actor subject) {
        return !subject.hasCapability(StatusActor.IS_INVADER);
    }


    /**
     * Retrieves the death rune dropped by the invader.
     *
     * @return the death rune.
     */
    @Override
    public Rune getDeathRune() {
        return new Rune(RandomNumberGenerator.getRandomIntInRange(this.minDeathRuneAmount, this.maxDeathRuneAmount));
    }

    /**
     * Resets the invader's state.
     *
     * @param map the game map.
     * @param rest indicates if the invader is resting.
     * @return the result of the reset operation.
     */
    @Override
    public String reset(GameMap map, boolean rest) {
        if (!rest) {
            DespawnAction despawn = new DespawnAction();
            return despawn.execute(this, map);
        }
        return this + " is not despawned";
    }

    /**
     * {@inheritDoc}
     *
     * @return true if the Invader is removable, false otherwise
     */
    @Override
    public boolean isRemovable() {
        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @return false as the Invader should not be removed on player rest
     */
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

