package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.gameactors.EnemyType;
import game.gameactors.StatusActor;
import game.gameactors.enemies.DeathRuneDroppper;
import game.gameactors.players.Player;
import game.items.Rune;
import game.items.PlayerRuneManager;

/**
 * An action executed if an actor is killed.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Tanul, Satoshi, Aditti
 * @version 1.0
 * @see Action
 *
 */
public class DeathAction extends Action {
    private final Actor attacker;
    private PlayerRuneManager rm = PlayerRuneManager.getInstance();

//    private ResetManager rm = ResetManager.getInstance();

    /**
     * Constructs a DeathAction object with the attacker who caused the death.
     *
     * @param actor The actor who caused the death.
     */
    public DeathAction(Actor actor) {
        this.attacker = actor;
    }

    /**
     * When the target is killed, the items and weapons carried by the target
     * will be dropped to the location in the game map where the target was.
     *
     * @param target The actor who was killed.
     * @param map The map the actor was on.
     * @return A string describing the result of the action to be displayed on the UI.
     */
    @Override
    public String execute(Actor target, GameMap map) {
        String result = System.lineSeparator() + target + " is killed";
        int droppedRuneAmount = 0;

        // if player is dead
        if (target.hasCapability(StatusActor.IS_PLAYER)) {
            // Player is dying
            Player player = (Player) target;
            String dropRuneMessage = new DropRuneAction().execute(player, map);

            player.respawn(map);

            result += System.lineSeparator() + dropRuneMessage;

        }


        // drop items & remove actor if needed
        if (target.hasCapability(StatusActor.IS_POB) || target.hasCapability(StatusActor.IS_NPC) ){
            // npc is dying

            // drop all items
            ActionList dropActions = new ActionList();
            for (Item item : target.getItemInventory()) {
                dropActions.add(item.getDropAction(target));
            }
            for (WeaponItem weapon : target.getWeaponInventory())
                dropActions.add(weapon.getDropAction(target));

            for (Action drop : dropActions)
                result += System.lineSeparator() + drop.execute(target, map);

            // remove actor
            map.removeActor(target);
        }

        // transfer Rune if needed
        if (this.attacker.hasCapability(StatusActor.IS_PLAYER) && (target.hasCapability(StatusActor.IS_NPC) || target.hasCapability(StatusActor.IS_POB))) {
            // player kills enemy
            // when player kills enemy, runes should be directly transferred
            Player player = (Player) this.attacker;

            DeathRuneDroppper enemy = (DeathRuneDroppper) target;
            if (target.hasCapability(EnemyType.EARTH_TYPE)) {
               player.heal(player.getMaxHP()); // heals to max hp
               result +=  System.lineSeparator() + this.attacker + " has been healed to their maxHP";
            }
            Rune droppedRune = enemy.getDeathRune();
            rm.increaseRune(droppedRune);
            droppedRuneAmount = droppedRune.getAmount();
            result += System.lineSeparator() + this.attacker + " collects " + droppedRuneAmount + " runes";
        }


        return result;
    }

    /**
     * Returns a string describing the death of an actor.
     *
     * @param actor The actor who died.
     * @return A string describing the death of the actor.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " kills.";
    }
}
