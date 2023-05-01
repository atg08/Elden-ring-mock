package game.gameactors.enemies.water;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.DespawnAction;
import game.behaviours.Behaviour;
import game.gameactors.EnemyType;
import game.gameactors.players.Player;

/**
 * A powerful enemy of the player that can perform a powerful slam attack
 * @author tanul
 */

public class GiantCrab extends OceanEnemy{
    /**
     * Constructor.
     *
     */
    public GiantCrab() {
        super("Giant Crab", 'C', 208, 318, 4961);
    }


    // slam cna be individual or area attack
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(208, "slams", 90);
    }

}
