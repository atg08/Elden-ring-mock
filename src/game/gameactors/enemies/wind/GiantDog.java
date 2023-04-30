package game.gameactors.enemies.wind;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.Behaviour;
import game.gameactors.EnemyType;

/**
 * A powerful enemy of the player that can perform a powerful headbutt attack
 * @author tanul
 */

public class GiantDog extends WindEnemy{
    /**
     * Constructor.
     * @author Tanul
     */
    public GiantDog() {
        super("Giant Dog", 'G', 693, 313, 1808);
        this.addCapability(EnemyType.WIND_TYPE);
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(314, "bashes", 90);
    }
}
