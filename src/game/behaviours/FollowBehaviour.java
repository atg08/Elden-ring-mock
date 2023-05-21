package game.behaviours;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import game.gameactors.StatusActor;

/**
 * The FollowBehaviour class implements the Behaviour interface and represents the behavior of an actor following a target actor.
 * It provides methods to calculate the next action for the actor to move closer to the target actor.
 *
 * @author Tanul, Satoshi, Aditti
 * @version 1.0
 * @see Behaviour
 */
public class FollowBehaviour implements Behaviour {

	private final Actor target;

	/**
	 * Constructor for FollowBehaviour class.
	 *
	 * @param subject the Actor to follow
	 */
	public FollowBehaviour(Actor subject) {
		this.target = subject;
	}


	/**
	 * This method gets the MoveActorAction that will move the actor one step closer to the target Actor.
	 *
	 * @param actor the actor that follows the target Actor
	 * @param map the current GameMap
	 * @return the MoveActorAction that will move the actor closer to the target Actor or null if it's not possible
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		if(!map.contains(target) || !map.contains(actor))
			return null;

		// check actor is supposed to follow
		if (!actor.hasCapability(StatusActor.FOLLOWING)){
			System.out.println("===================================");
			return null;
		}

		Location here = map.locationOf(actor);
		Location there = map.locationOf(target);

		int currentDistance = distance(here, there);
		for (Exit exit : here.getExits()) {
			Location destination = exit.getDestination();
			if (destination.canActorEnter(actor)) {
				int newDistance = distance(destination, there);
				if (newDistance < currentDistance) {
					actor.addCapability(StatusActor.FOLLOWING);
					return new MoveActorAction(destination, exit.getName());
				}
			}
		}

		return null;
	}

	/**
	 * Compute the Manhattan distance between two locations.
	 *
	 * @param a the first location
	 * @param b the first location
	 * @return the number of steps between a and b if you only move in the four cardinal directions.
	 */
	private int distance(Location a, Location b) {
		return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
	}
}
