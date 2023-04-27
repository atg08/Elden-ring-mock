package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AreaAttackAction;
import game.actions.AttackAction;
import game.gameactors.EnemyType;
import game.gameactors.StatusActor;
import game.gameactors.enemies.Enemy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AttackBehaviour implements Behaviour {

    private final Random random = new Random();

    @Override
    public Action getAction(Actor actor, GameMap map) {
        ArrayList<Action> actions = new ArrayList<>();
        Enemy enemy = (Enemy) actor;

        boolean isTargetedAction = isTargetedAction();

        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            Actor targetActor = destination.getActor();

            boolean canAttack = enemy.canTarget(targetActor);

            // area attack -> if we find at least one attackable actor -> return AreaAttackAction
            if (canAttack && isTargetedAction){
                List<WeaponItem> weaponList = actor.getWeaponInventory();
                if (weaponList.size() == 0){
                    // intrinsic weapon attack
                    return new AreaAttackAction();
                }else{
                    // this method returns AreaAttackAction/special attack
                    return weaponList.get(0).getSkill(actor);
                }
            }

            // targeted attack -> collect all possible enemies around
            if (canAttack){
                List<WeaponItem> weaponList = actor.getWeaponInventory();
                if (weaponList.size() == 0){
                    // intrinsic weapon attack
                    actions.add(new AttackAction(targetActor, exit.getName()));
                }else{
                    // regular weapon attack
                    actions.add(new AttackAction(targetActor, exit.getName(), weaponList.get(0)));
                }
            }

        }
        if (!actions.isEmpty()) {
            return actions.get(random.nextInt(actions.size()));
        }
        else {
            return null;
        }
    }

    private boolean isTargetedAction(){
        return random.nextInt(0, 1) == 0;
    }
}
