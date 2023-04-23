package game.gameactors.players;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.Reset.ResetManager;
import game.gameactors.StatusActor;
import game.weapons.Club;
import game.Reset.Resettable;
import game.Status;

/**
 * Class representing the Player. It implements the Resettable interface.
 * It carries around a club to attack a hostile creature in the Lands Between.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public abstract class Player extends Actor implements Resettable {

	private final Menu menu = new Menu();
	private ResetManager rm;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(int hitPoints) {
		super("Tarnished", '@', hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addWeaponToInventory(new Club());
		this.addCapability(StatusActor.CAN_REST);
		rm.registerResettable(this);
	}

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	/**
	 * this reset is called when player dies or rests at the site of lost grace
	 * @param actor
	 * @param map
	 */
	@Override
	public String reset(Actor actor, GameMap map) {
		this.heal(getMaxHp());
		return "Player health is reset to " + this.printHp();
	}



}
