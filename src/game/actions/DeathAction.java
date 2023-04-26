package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.gameactors.StatusActor;
import game.gameactors.players.Player;

/**
 * An action executed if an actor is killed.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class DeathAction extends Action {
    private Actor attacker;

    public DeathAction(Actor actor) {
        this.attacker = actor;
    }

    /**
     * When the target is killed, the items & weapons carried by target
     * will be dropped to the location in the game map where the target was
     *
     * @param target The actor performing the action.
     * @param map The map the actor is on.
     * @return result of the action to be displayed on the UI
     */
    @Override
    public String execute(Actor target, GameMap map) {
        String result = "";

        if (target.hasCapability(StatusActor.CAN_RESPAWN) && target.hasCapability(StatusActor.HOSTILE_TO_ENEMY)) {
            Player player = (Player) target;
            player.respawn(map);

            // add the drop runes

            return  result;
        } else {
            // only happens for enemies
            ActionList dropActions = new ActionList();
            // drop all items
            for (Item item : target.getItemInventory())
                dropActions.add(item.getDropAction(target));
            for (WeaponItem weapon : target.getWeaponInventory())
                dropActions.add(weapon.getDropAction(target));
            for (Action drop : dropActions)
                drop.execute(target, map);
            // remove actor
            map.removeActor(target);
            result += System.lineSeparator() + menuDescription(target);
            return result;

            // TODO make sure to trigger drop rune action (note: however for enemy when it's killed by the player
            //  it automatically transfers its runes to the player so its's not shown in the amp)
            // HOWEVER, for player when it's dead, the rune must be dropped
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " is killed.";
    }
}
