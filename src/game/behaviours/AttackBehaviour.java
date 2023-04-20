package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.gameactors.EnemyType;
import game.gameactors.StatusActor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AttackBehaviour implements Behaviour {

    private final Random random = new Random();

    @Override
    public Action getAction(Actor actor, GameMap map) {
        ArrayList<Action> actions = new ArrayList<>();

        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            Actor targetActor = destination.getActor();

            if (targetActor.hasCapability(StatusActor.HOSTILE_TO_ENEMY)) {
                // target actor is a player
                // TODO

            }else{
                // target actor is an enemy
                List<EnemyType> actorTypeList = actor.findCapabilitiesByType(EnemyType.class);
                EnemyType actorType = actorTypeList.get(0); // the type we are looking for

                List<EnemyType> targetActorTypeList = actor.findCapabilitiesByType(EnemyType.class);
                EnemyType targetActorType = targetActorTypeList.get(0); // the type we are looking for

                if (actorType.equals(targetActorType)){
                    List<WeaponItem> weaponList = actor.getWeaponInventory();
                    if (weaponList.size() == 0){
                        actions.add(new AttackAction(targetActor, exit.getName()));
                    }else{
                        actions.add(new AttackAction(targetActor, exit.getName(), weaponList.get(0)));
                    }
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
}
