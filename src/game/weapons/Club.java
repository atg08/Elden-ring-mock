package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.runes.Rune;

/**
 * A simple weapon that can be used to attack the enemy.
 * It deals 103 damage with 80% hit rate
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Aditti
 *
 */

public class Club extends WeaponItem implements Purchasable, Sellable {

    /**
     * Constructor
     */
    public Club() {
        super("Club", '!', 103, "bonks", 80);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {}

    @Override
    public Action getSkill(Actor target, String direction){
        return new AttackAction(target, direction, this);
    }

    @Override
    public Rune getPurchasingPrice() {
        return new Rune(600);
    }

    @Override
    public WeaponItem restock() {
        return new Club();
    }

    @Override
    public Rune getSellingPrice() {
        return new Rune(100);
    }
}

