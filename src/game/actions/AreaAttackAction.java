package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.gameactors.StatusActor;
import game.gameactors.enemies.Enemy;

public class AreaAttackAction extends Action {

    private Weapon weapon;

    /**
     * Constructor for an AreaAttackAction that uses a specified weapon.
     *
     * @param weapon the weapon to use for the area attack
     */
    public AreaAttackAction(Weapon weapon) {
        this.weapon = weapon;
    }

    /**
     *
     * the deafult constructor used when the attack is done by the intrinsic weapon
     */
    public AreaAttackAction() {}


    /**
     * Performs an area attack using the specified weapon or the actor's intrinsic weapon.
     *
     * @param actor the actor performing the area attack
     * @param map the game map on which the area attack is being performed
     * @return a string describing the results of the area attack
     */
    public String execute(Actor actor, GameMap map) {
        String results = actor.toString() + "attacks his surrounding!";

        // check all exits

        if (this.weapon != null){
            for (Exit exit : map.locationOf(actor).getExits()){
                Location destination = exit.getDestination();
                Actor targetActor = destination.getActor();
                boolean canAttack = true;
                if (targetActor == null){
                    continue;
                }
                if (targetActor.hasCapability(StatusActor.IS_ENEMY) && actor.hasCapability(StatusActor.IS_ENEMY)){
                    Enemy enemy = (Enemy) actor;
                    canAttack = enemy.canTarget(targetActor);
                }
                if (canAttack){
                    results += System.lineSeparator() + new AttackAction(targetActor, exit.getName(), this.weapon).execute(actor, map);
                }
            }
        }else{
            for (Exit exit : map.locationOf(actor).getExits()){
                Location destination = exit.getDestination();
                Actor targetActor = destination.getActor();
                boolean canAttack = true;
                if (targetActor == null){
                    continue;
                }
                if (targetActor.hasCapability(StatusActor.IS_ENEMY) && actor.hasCapability(StatusActor.IS_ENEMY)){
                    Enemy enemy = (Enemy) actor;
                    canAttack = enemy.canTarget(targetActor);
                }
                if (canAttack){
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
