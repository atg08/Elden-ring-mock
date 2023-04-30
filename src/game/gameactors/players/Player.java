package game.gameactors.players;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.Reset.ResetManager;
import game.gameactors.StatusActor;
import game.runes.Rune;
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
public abstract class Player extends Actor implements Resettable, Respawnable {

	private final Menu menu = new Menu();
	private ResetManager rm = ResetManager.getInstance();
	protected Rune runes = new Rune();
	protected SiteOfLostGrace respawnPoint;
	private Location previousLocation;

	public SiteOfLostGrace getRespawnPoint() {
		return respawnPoint;
	}

	public void setRespawnPoint(SiteOfLostGrace respawnPoint) {
		this.respawnPoint = respawnPoint;
	}

	/**
	 * Constructor.
	 *
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(int hitPoints) {
		super("Tarnished", '@', hitPoints);
		this.addCapability(StatusActor.HOSTILE_TO_ENEMY);
		this.addCapability(StatusActor.CAN_RESPAWN);
		this.addWeaponToInventory(new Club());
		this.addCapability(StatusActor.CAN_REST);
		this.addItemToInventory(new FlaskOfCrimsonTears());
		this.setRespawnPoint(TheFirstStep.getInstance());
		rm.registerResettable(this);

		this.addItemToInventory(new Rune());  // player always starts with 0 rune
	}

	@Override
	public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
		ActionList actions = new ActionList();
		actions.add(new ConsumeAction(this.getItemInventory().get(0)));
		return actions;
	}

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		// record player's current location
		this.previousLocation = map.locationOf(this);

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// add area attack
		if (checkEnemyExistenceAround(map.locationOf(this))){
			for (WeaponItem weaponItem: this.getWeaponInventory()){
				if (weaponItem.hasCapability(WeaponSkill.AREA_ATTACK)){
					actions.add(new AreaAttackAction(weaponItem));
				}
			}

			// TODO if player's intrinsic weapon has area attack ability, add it here
		}


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
		this.heal(this.getMaxHp());
		return "Player health is reset to " + this.printHp();
	}

	@Override
	public boolean isRemovable() {
		return false;
	}

	public Rune getExistingRune(){
		Rune existingRune = (Rune) this.getItemInventory().
				stream()
				.filter(item -> "rune".equals(item.toString()))
				.findFirst()
				.orElse(null);

		return existingRune;
	}

	public void increaseRune(Rune rune){

		Rune existingRune = this.getExistingRune();
		// Note: it shouldn't be null because we instantiate new Rune everytime we instantiate a player
		if (existingRune != null){
			existingRune.increaseRune(rune);
		}

	}

	public boolean decreaseRune(Rune rune){
		Rune existingRune = getExistingRune();
		return existingRune.decreaseRune(rune);
	}

	@Override
	public void respawn(GameMap map) {
		map.removeActor(this);
		map.addActor(this, getRespawnPoint().getSiteLocation());
	}
	public boolean checkEnemyExistenceAround(Location location) {
		for (Exit exit : location.getExits()) {
			Location destination = exit.getDestination();
			Actor targetActor = destination.getActor();

			if (targetActor.hasCapability(StatusActor.IS_ENEMY)) {
				return true;
			}
		}

		return false;
	}

	public Location getPlayerPreviousLocation(){
		return this.previousLocation;
	}
}
