package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.gameactors.StatusActor;

/**
 * A class that represents the floor inside a building.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 *
 */
public class Floor extends Ground {
	public Floor() {
		super('_');
	}

	@Override
	public boolean canActorEnter(Actor actor) {
		return actor.hasCapability(StatusActor.HOSTILE_TO_ENEMY);
	}
}