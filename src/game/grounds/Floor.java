package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.gameactors.StatusActor;

/**
 * A class that represents the floor inside a building.
 *
 * This class extends the Ground class, and specifies the behavior of the
 * floor inside a building, such as which actors can enter it.
 *
 * Created by: Riordan D. Alfredo
 * Modified by: Tanul , Satoshi , Aditti
 * @version 1.0.0
 */


public class Floor extends Ground {

	/**
	 * Constructor for the Floor class.
	 */
	public Floor() {
		super('_');
	}


	/**
	 * Determines whether the given actor can enter the floor.
	 *
	 * This method checks whether the actor has the capability to enter the floor,
	 * which is determined by whether the actor is a player or a trader.
	 *
	 * @param actor The actor that is attempting to enter the floor.
	 * @return True if the actor has the capability to enter the floor, false otherwise.
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return actor.hasCapability(StatusActor.IS_PLAYER) || actor.hasCapability(StatusActor.IS_TRADER);
	}
}
