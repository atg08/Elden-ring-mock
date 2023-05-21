package game.gameactors.enemies.wind;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.Behaviour;
import game.gameactors.EnemyType;
import game.gameactors.players.Player;

/**
 * This class represents a type of WindEnemy called GiantDog, which has a specific set of stats and capabilities.
 * It extends the WindEnemy class and overrides the getIntrinsicWeapon method to return a specific weapon.
 *
 * @author Tanul , Satoshi , Aditti
 * @version 1.0
 * @see WindEnemy
 */

public class GiantDog extends WindEnemy{


    /**
     * Constructor for GiantDog.
     */
    public GiantDog() {
        super("Giant Dog", 'G', 693, 313, 1808);
        this.addCapability(EnemyType.WIND_TYPE);
    }


    /**
     * Returns the intrinsic weapon of the GiantDog.
     *
     * @return the intrinsic weapon of the GiantDog, which is an instance of the IntrinsicWeapon class.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(314, "bashes", 90);
    }
}
