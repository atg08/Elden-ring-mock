package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;

public class AreaAttackAction extends Action {

    private Actor target;
    private Weapon weapon;

    public AreaAttackAction(Actor target, Weapon weapon) {
        this.target = target;
        this.weapon = weapon;
    }

    public AreaAttackAction(Actor target) {
        this.target = target;
    }

    public String execute(Actor actor, GameMap map) {
        String results = "";

        // check all exits

        if (this.weapon != null){
            for (Exit exit : map.locationOf(actor).getExits()){
                Location destination = exit.getDestination();
                Actor targetActor = destination.getActor();

                if (targetActor != null){
                    results += new AttackAction(this.target, exit.getName(), this.weapon).execute(actor, map);
                }
            }
        }else{
            for (Exit exit : map.locationOf(actor).getExits()){
                Location destination = exit.getDestination();
                Actor targetActor = destination.getActor();

                if (targetActor != null){
                    results += new AttackAction(this.target, exit.getName()).execute(actor, map);
                }
            }
        }

        return results;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
