package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.gameactors.EnemyType;
import game.gameactors.StatusActor;
import game.gameactors.enemies.Enemy;
import game.gameactors.players.Player;
import game.runes.Rune;

import static game.gameactors.EnemyType.EARTH_TYPE;

/**
 * An action executed if an actor is killed.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class DeathAction extends Action {
    private Actor attacker;

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
        String result = "";
        int droppedRuneAmount = 0;

        if (target.hasCapability(StatusActor.CAN_RESPAWN) && target.hasCapability(StatusActor.IS_PLAYER)) {
            // Player is dying
            Player player = (Player) target;

            Rune droppedRune = player.getDeathRune();
            droppedRune.setRuneLocation(map.locationOf(target));
            result = new DropItemAction(droppedRune).execute(player, map);

//            rm.run(player, map);
            player.respawn(map);

            droppedRuneAmount = droppedRune.getAmount();
            result += System.lineSeparator() + menuDescription(target)
                    + System.lineSeparator() + target + " dropped " + droppedRuneAmount + " runes";

        } else {
            // enemy is dying

            // drop all items
            ActionList dropActions = new ActionList();
            for (Item item : target.getItemInventory()) {
                dropActions.add(item.getDropAction(target));
            }
            for (WeaponItem weapon : target.getWeaponInventory())
                dropActions.add(weapon.getDropAction(target));

            for (Action drop : dropActions)
                drop.execute(target, map);

            // transfer Rune
            if (this.attacker.hasCapability(StatusActor.IS_PLAYER) && target.hasCapability(StatusActor.IS_ENEMY)) {
                // player kills enemy
                // when player kills enemy, runes should be directly transferred
                Player player = (Player) this.attacker;
                Enemy enemy = (Enemy) target;
                if (target.hasCapability(EnemyType.EARTH_TYPE)) {
                   player.heal(player.getMaxHP()); // heals to max hp
                   result +=  System.lineSeparator() + this.attacker + " has been healed to their maxHP";
                }
                Rune droppedRune = enemy.getDeathRune();
                player.increaseRune(droppedRune);
                droppedRuneAmount = droppedRune.getAmount();
                result += System.lineSeparator() + menuDescription(target) + System.lineSeparator() + this.attacker + " collects " + droppedRuneAmount + " runes";
            }else{
                // enemy kills enemy
                result += System.lineSeparator() + menuDescription(target);
            }

            // remove actor
            map.removeActor(target);
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
        return actor + " is killed.";
    }
}
