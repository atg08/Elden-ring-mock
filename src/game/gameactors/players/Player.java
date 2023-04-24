package game.gameactors.players;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.Reset.ResetManager;
import game.actions.ConsumeAction;
import game.gameactors.StatusActor;
import game.items.FlaskOfCrimsonTears;
import game.runes.Rune;
import game.Reset.Resettable;
import game.Status;
import game.weapons.Club;

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
	protected Rune runes = new Rune();

	/**
	 * Constructor.
	 *
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(int hitPoints) {
		super("Tarnished", '@', hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addWeaponToInventory(new Club());
		this.addCapability(StatusActor.CAN_REST);
		this.addItemToInventory(new FlaskOfCrimsonTears());
		rm.registerResettable(this);
	}

	@Override
	public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
		ActionList actions = new ActionList();
		actions.add(new ConsumeAction(this.getItemInventory().get(0)));
		return actions;
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

	@Override
	public boolean isRemovable() {
		return false;
	}

	public void increaseRune(Rune rune){
		this.runes.increaseRune(rune);
	}

	public boolean decreaseRune(Rune rune){
		return this.runes.decreaseRune(rune);
	}



}
