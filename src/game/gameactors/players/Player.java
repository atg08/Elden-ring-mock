package game.gameactors.players;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.FancyMessage;
import game.reset.ResetManager;
import game.reset.Respawnable;
import game.actions.AreaAttackAction;
import game.environments.SiteOfLostGrace;
import game.environments.TheFirstStep;
import game.gameactors.StatusActor;
import game.gameactors.enemies.DeathRuneDroppper;
import game.items.FlaskOfCrimsonTears;
import game.runes.Rune;
import game.reset.Resettable;
import game.weapons.WeaponSkill;

import java.util.ArrayList;

/**
 * This abstract class represents the player in the game. It implements the Resettable and Respawnable interfaces.
 * It carries around a club to attack a hostile creature in the Lands Between.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Tanul, Satoshi, Aditti
 *
 */
public abstract class Player extends Actor implements Resettable, Respawnable, DeathRuneDroppper {

	private final Menu menu = new Menu();
	/**
	 * The ResetManager object for the player.
	 */
	private ResetManager rm = ResetManager.getInstance();
	/**
	 * The Rune object for the player.
	 */
	protected Rune runes = new Rune();
	/**
	 * The respawn point for the player.
	 */
	protected static SiteOfLostGrace respawnPoint;
	/**
	 * The previous location of the player.
	 */
	private Location previousLocation;
	/**
	 * Returns the location of the respawn point.
	 * @return the location of the respawn point
	 */
	public SiteOfLostGrace getRespawnPoint() {
		return respawnPoint;
	}
	/**
	 * Sets the respawn point.
	 * @param respawnPoint the respawn point to set
	 */
	public void setRespawnPoint(SiteOfLostGrace respawnPoint) {
		this.respawnPoint = respawnPoint;
	}



	private static ArrayList<GameMap> mapsAccessible = new ArrayList<>();

	public static ArrayList<GameMap> getMapsAccessible() {
		return mapsAccessible;
	}
	public static void addMapAccessible(GameMap map){

		mapsAccessible.add(map);
	}

	/**
	 *
	 * @return players current hitpoints
	 */
	public int getMaxHP(){
		return this.getMaxHp();
	}

	/**
	 * Constructs a Player object with the specified number of hitpoints.
	 * @param hitPoints the number of hitpoints the player has
	 */
	public Player(int hitPoints) {
		super("Tarnished", '@', hitPoints);
		this.addCapability(StatusActor.IS_PLAYER);
		this.addCapability(StatusActor.CAN_RESPAWN);
//		this.addWeaponToInventory(new Club());
		this.addCapability(StatusActor.CAN_REST);
		this.addItemToInventory(new FlaskOfCrimsonTears());
		rm.registerResettable(this);
		this.addItemToInventory(new Rune());  // player always starts with 0 rune
		respawnPoint = TheFirstStep.getInstance();

	}

	/**
	 * Displays the player's name, hitpoints, and runes.
	 * @param actions the list of actions available to the player
	 * @param lastAction the last action the player performed
	 * @param map the game map
	 * @param display the game display
	 * @return the action selected by the player
	 */

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

		display.println(this.name + " (" + this.hitPoints +"/" + this.getMaxHp() + ")" + ", runes: " + getExistingRune().getAmount());

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
	 * Resets the player's hitpoints when the player dies or rests at the site of lost grace.
	 * @param actor the actor to reset
	 * @param map the game map
	 * @return a string indicating that the player's health has been reset
	 */
	@Override
	public String reset(Actor actor, GameMap map) {
		this.resetMaxHp(this.getMaxHp());
		return "Player health is reset to " + this.printHp();
	}

	/**
	 *
	 * If this resettable is removable after reset
	 * @return false
	 */
	@Override
	public boolean isRemovable() {
		return false;
	}

	/**

	 Retrieves the existing Rune object from the player's inventory.

	 @return The existing Rune object, or null if there is none.
	 */
	public Rune getExistingRune(){
		Rune existingRune = (Rune) this.getItemInventory().
				stream()
				.filter(item -> "rune".equals(item.toString()))
				.findFirst()
				.orElse(null);

		return existingRune;
	}
	/**

	 Increases the quantity of the given Rune object in the player's inventory.

	 @param rune The Rune object to increase.
	 */
	public void increaseRune(Rune rune){

		Rune existingRune = this.getExistingRune();
		// Note: it shouldn't be null because we instantiate new Rune everytime we instantiate a player
		if (existingRune != null){
			existingRune.increaseRune(rune);
//			System.out.println("runes " + existingRune.getAmount());
		}

	}


	/**

	 Decreases the quantity of the given Rune object in the player's inventory.
	 @param rune The Rune object to decrease.
	 @return True if the Rune was successfully decreased, false otherwise.
	 */
	public boolean decreaseRune(Rune rune){
		Rune existingRune = getExistingRune();
		return existingRune.decreaseRune(rune);
	}


	/**
	 * Displays the you died message then resets the game, player to respawn point
	 * @param map the GameMap on which the object should respawn
	 */
	@Override
	public void respawn(GameMap map) {
		// BEHOLD, ELDEN RING
		for (String line : FancyMessage.YOU_DIED.split("\n")) {
			new Display().println(line);
			try {
				Thread.sleep(200);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
		this.addItemToInventory(new Rune());
		rm.run(this,map);
		map.moveActor(this, getRespawnPoint().getSiteLocation());


	}

	/**

	 Checks if there is an enemy actor in any of the exits of the given location.

	 @param location The location to check for enemy actors.

	 @return True if there is an enemy actor, false otherwise.
	 */
	public boolean checkEnemyExistenceAround(Location location) {
		for (Exit exit : location.getExits()) {
			Location destination = exit.getDestination();
			Actor targetActor = destination.getActor();

			if (targetActor != null && targetActor.hasCapability(StatusActor.IS_ENEMY)) {
				return true;
			}
		}

		return false;
	}


	/**

	 Retrieves the player's previous location.
	 @return The player's previous location.
	 */
	public Location getPlayerPreviousLocation(){
		return this.previousLocation;
	}

	@Override
	public Rune getDeathRune(){
		Rune droppedRune = this.getExistingRune();

		// ensure that rune can be dropped; note we don't need to toggle back this because when this rune is picked
		// up, its amount will be added to another new instance of Rune which is not portable
		droppedRune.togglePortability();

		// register this rune as resettable
		droppedRune.registerAsResettable();

		return droppedRune;
	}

//	@Override
//	public boolean isRemovableOnPlayerRest() {
//		return false;
//	}
}


