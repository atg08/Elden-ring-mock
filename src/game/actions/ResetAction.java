package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Reset.Resettable;
import game.gameactors.EnemyType;
import game.gameactors.StatusActor;
import game.gameactors.enemies.Enemy;
import game.gameactors.enemies.graveyard.Skeleton;
import game.gameactors.enemies.water.OceanEnemy;
import game.gameactors.enemies.wind.WindEnemy;
import game.gameactors.players.Player;

public class ResetAction extends Action {

    private Resettable r;

    public ResetAction(Resettable r) {
        this.r = r;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String ret = r.reset(actor,map);
        return ret;
//        if (actor.hasCapability(StatusActor.HOSTILE_TO_ENEMY)){
//            Player actor2 = (Player) actor;
//            return actor2.reset(actor,map);
//        }
//        // StatusActor.IS_ENEMY
//
//        Enemy actor2 = (Enemy) actor;
//        if (actor2.hasCapability(EnemyType.SKELETON_TYPE)) {
//            Skeleton actor3 = (Skeleton) actor;
//            return actor3.reset(actor3,map);
//        } else if (actor2.hasCapability(EnemyType.OCEAN_TYPE)) {
//            OceanEnemy actor3 = (OceanEnemy) actor;
//            return actor3.reset(actor3,map);
//        } else if (actor2.hasCapability(EnemyType.WIND_TYPE)) {
//            WindEnemy actor3 = (WindEnemy) actor;
//            return actor3.reset(actor3,map); // return a string from Despawn action
//        }
//
//        // need to implement for item
//        return "map has been reset";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "map has been reset";
    }
}
