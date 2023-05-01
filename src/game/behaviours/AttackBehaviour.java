package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AreaAttackAction;
import game.actions.AttackAction;
import game.gameactors.enemies.Enemy;
import game.gameactors.players.Player;
import game.weapons.WeaponSkill;

import java.util.ArrayList;
import java.util.Random;

public class AttackBehaviour implements Behaviour {

    private final Random random = new Random();
    private Player player;

    public AttackBehaviour(Player player){
        this.player = player;
    }

    // assume enemy does not own any weapons with a special skill (like Uchigatana)
    @Override
    public Action getAction(Actor actor, GameMap map) {
        // ensure that player has not moved in the same turn
        if (map.locationOf(player) != this.player.getPlayerPreviousLocation()){
            return null;
        }

        ArrayList<Action> actions = new ArrayList<>();
        Enemy enemy = (Enemy) actor;

        boolean isTargetedAction = this.detTargetedAction();
        WeaponItem weaponItem = null;
        if (actor.getWeaponInventory().size() != 0){
            weaponItem = actor.getWeaponInventory().get(0);
            boolean canTargetAttack = weaponItem.hasCapability(WeaponSkill.AREA_ATTACK);
            boolean canAreaAttack = weaponItem.hasCapability(WeaponSkill.TARGETED_ATTACK);

            if (canTargetAttack && !canAreaAttack){
                isTargetedAction = true;
            } else if (!canTargetAttack && canAreaAttack){
                isTargetedAction = false;
            }
        }


        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            Actor targetActor = destination.getActor();

            if (targetActor == null){
                continue;
            }

            // area attack -> if we find at least one attack-able actor -> return AreaAttackAction
            boolean canAttack = enemy.canTarget(targetActor);
            if (canAttack) {
                if (!isTargetedAction) {
                    if (weaponItem != null) {
                        // intrinsic weapon attack
                        return new AreaAttackAction(weaponItem);
                    } else {
                        // this method returns AreaAttackAction/special attack
                        return new AreaAttackAction();
                    }
                }
            }
            // targeted attack -> collect all possible enemies around
            if (canAttack){
                if (weaponItem == null){
                    // intrinsic weapon attack
                    actions.add(new AttackAction(targetActor, exit.getName()));
                }else{
                    // regular weapon attack
                    actions.add(new AttackAction(targetActor, exit.getName(), weaponItem));
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

    private boolean detTargetedAction(){
        return random.nextInt(2) == 0;
    }
}
