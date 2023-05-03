package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

/**
 * This class represents a wall object in the game world. It extends the Ground class
 * and is represented by the character '#'. It blocks actors from entering
 *
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by: Tanul , Satoshi , Aditti
 * @version 1.0.0
 *
 */
public class Wall extends Ground {


	/**
	 * Constructor for creating a new Wall object with the character '#'.
	 */
	public Wall() {
		super('#');
	}


	/**
	 * Determines if an actor is allowed to enter this ground. As Walls are impassable,
	 * this method always returns false.
	 *
	 * @param actor the actor attempting to enter the ground
	 * @return false as actors cannot enter walls
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}


	/**
	 * Determines if thrown objects are blocked by this ground. As Walls block thrown
	 * objects, this method always returns true.
	 *
	 * @return true as thrown objects are blocked by walls
	 */
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
