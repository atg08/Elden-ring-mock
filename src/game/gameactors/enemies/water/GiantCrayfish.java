package game.gameactors.enemies.water;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.Behaviour;
import game.gameactors.EnemyType;

/**
 * A powerful enemy of the player that can perform a powerful slam attack
 * @author tanul
 */

public class GiantCrayfish extends OceanEnemy{
    /**
     * Constructor.
     * @author Tanul
     */
    public GiantCrayfish() {
        super("Giant Crayfish", 'R', 4803, 500, 2374);
    }

    // giant Pincer
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(527, "slams", 100);
    }
}
