package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.gameactors.players.Player;
import game.items.RuneManager;
import game.weapons.Sellable;

/**
 * Action to sell weapons from trader to an actor.
 * @author Satoshi Kashima
 */
public class SellAction extends Action {
    /**
     * Weapon to be sold by player.
     */
    private WeaponItem weapon;

    RuneManager rm = RuneManager.getInstance();

    /**
     * Constructor. Requires rune as input.
     *
     * @param weapon the weapon to be sold
     */
    public SellAction(WeaponItem weapon){
        this.weapon = weapon;
    }

    /**
     * Executes the sell rune action.
     * It increases the rune of the player and removes the weapon from the player's inventory.
     * Note: as precondition, the player should possess the weapon passed to the constructor.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a suitable description to display in the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        Sellable sellableWeapon = (Sellable) this.weapon;
        player.removeWeaponFromInventory(this.weapon);
        rm.increaseRune(sellableWeapon.getSellingPrice());
        return  "Tarnished sold " + this.weapon.toString();
    }

    /**
     * Describes which weapon is sold and for how much.
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        Sellable sellableWeapon = (Sellable) this.weapon;
        return "Tarnished sells " + this.weapon.toString() + " for " + sellableWeapon.getSellingPrice().getAmount();
    }
}
