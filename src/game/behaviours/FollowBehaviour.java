package game.behaviours;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import game.gameactors.enemies.IFollowable;
import game.gameactors.enemies.IFollower;

/**
 * A class that figures out a MoveAction that will move the actor one step 
 * closer to a target Actor.
 * @see edu.monash.fit2099.demo.mars.Application
 *
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 *
 */
public class FollowBehaviour implements Behaviour {


	/**
	 * This method gets the MoveActorAction that will move the actor one step closer to the target Actor.
	 *
	 * @param actor the actor that follows the target Actor
	 * @param map the current GameMap
	 * @return the MoveActorAction that will move the actor closer to the target Actor or null if it's not possible
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		//todo
		System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
		// if this enemy has been following someone, follow him;
		IFollower follower = (IFollower) actor;
		IFollowable target = follower.getFollowingActor();

		// else, find someone who this actor can follow
//		if (target == null){
//			System.out.println("===================================================");
//			target = follower.getANewCandidateActorToFollow(map.locationOf(actor).getExits());
//			System.out.println("aaaaaaaaaaaaaaaaaaaaa"+ target);
//		}

		// if there is no enemy to follow still or the location is the same, return null
		if (target == null || target.getPlayerPreviousLocation() == map.locationOf((Actor) target))
			return null;

		if(!map.contains((Actor) target) || !map.contains(actor))
			return null;
		
		Location here = map.locationOf(actor);
		Location there = map.locationOf((Actor) target);

		int currentDistance = distance(here, there);
		for (Exit exit : here.getExits()) {
			Location destination = exit.getDestination();
			if (destination.canActorEnter(actor)) {
				int newDistance = distance(destination, there);

				// note: if player or any followable stays in the same position from the previous iteration
				// this if statement will always yield false -> cannot perform FollowBehaviour. -> performs ActionBehaviour
				if (newDistance < currentDistance) {
					follower.setFollowingActor(target);
					System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
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