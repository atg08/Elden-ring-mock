package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;

public class AreaAttackAction extends Action {

    private Weapon weapon;

    public AreaAttackAction(Weapon weapon) {
        this.weapon = weapon;
    }

    /**
     *
     * the deafult constructor used when the attack is done by the intrinsic weapon
     */
    public AreaAttackAction() {}

    public String execute(Actor actor, GameMap map) {
        String results = actor.toString() + "attacks his surrounding!";

        // check all exits

        if (this.weapon != null){
            for (Exit exit : map.locationOf(actor).getExits()){
                Location destination = exit.getDestination();
                Actor targetActor = destination.getActor();

                if (targetActor != null){
                    results += System.lineSeparator() + new AttackAction(targetActor, exit.getName(), this.weapon).execute(actor, map);
                }
            }
        }else{
            for (Exit exit : map.locationOf(actor).getExits()){
                Location destination = exit.getDestination();
                Actor targetActor = destination.getActor();

                if (targetActor != null){
                    results += System.lineSeparator() + new AttackAction(targetActor, exit.getName()).execute(actor, map);
                }
            }
        }

        return results;
    }

    @Override
    public String menuDescription(Actor actor) {
        if (this.weapon != null){
            return "Do area attack using " + this.weapon;
        }
        return "Do area attack using " + actor.getIntrinsicWeapon().toString();

    }
}
