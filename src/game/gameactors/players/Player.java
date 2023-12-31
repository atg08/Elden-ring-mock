package game.gameactors.players;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.FancyMessage;
import game.actions.ConsumeAction;
import game.items.*;
import game.reset.ResetManager;
import game.reset.Respawnable;
import game.actions.AreaAttackAction;
import game.gameactors.StatusActor;
import game.gameactors.enemies.DeathRuneDroppper;
import game.reset.Resettable;
import game.weapons.WeaponSkill;

import java.util.ArrayList;

/**
 * This abstract class represents the player in the game. It implements the Resettable and Respawnable interfaces.
 * It carries around a club to attack a hostile creature in the Lands Between.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Tanul, Satoshi, Aditti
 * @version 1.0
 * @see Actor
 * @see Resettable
 * @see Respawnable
 * @see DeathRuneDroppper
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

	protected PlayerRuneManager runeManager = PlayerRuneManager.getInstance();

	/**
	 * The previous location of the player.
	 */
	private Location previousLocation;


	private static Location respawnLocation;

	private static ArrayList<GameMap> mapsAccessible = new ArrayList<>();

	protected WeaponItem originalWeapon;


	/**
	 * Constructs a Player object with the specified number of hitpoints.
	 * @param hitPoints the number of hitpoints the player has
	 */
	public Player(int hitPoints) {
		super("Tarnished", '@', hitPoints);
		this.addCapability(StatusActor.IS_PLAYER);
		this.addCapability(StatusActor.CAN_RESPAWN);
		this.addCapability(StatusActor.HOSTILE_TO_ENEMY);
		this.addCapability(StatusActor.CAN_REST);
		this.addItemToInventory(new FlaskOfCrimsonTears());
		this.rm.registerResettable(this);
//		this.addItemToInventory(new Rune(100));  // player always starts with 0 rune
		Player.maxHP = hitPoints;
		this.runeManager.increaseRune(new Rune());

		this.addItemToInventory(new RemembranceOfTheGrafted());  // start with a sample RemembranceOfTheGrafted
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

		display.println(this.name + " (" + this.hitPoints +"/" + this.getMaxHp() + ")" + ", runes: " + runeManager.getPlayerRune().getAmount());

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

		for (Item i : this.getItemInventory()){
			if (i.hasCapability(ItemUsage.IS_GOLDEN_RUNE)){
				actions.add(new ConsumeAction((Consumable) i));
			}
		}


		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	/**
	 * Resets the player's hitpoints when the player dies or rests at the site of lost grace.
	 *
	 * @param map the game map
	 * @return a string indicating that the player's health has been reset
	 */
	@Override
	public String reset(GameMap map, boolean rest) {
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

	public Location getRespawnLocation() {
		return respawnLocation;
	}

	public static void setRespawnLocation(Location _respawnLocation) {
		respawnLocation = _respawnLocation;
	}

	private static int maxHP;

	/**
	 *
	 * @return players current hitpoints
	 */
	public static int getMaxHP(){
		return maxHP;
	}

	/**

	 Retrieves the existing Rune object from the player's inventory.

	 @return The existing Rune object, or null if there is none.
	 */
//	public Rune getExistingRune(){
//
//
//		Rune existingRune = (Rune) this.getItemInventory().
//				stream()
//				.filter(item -> "rune".equals(item.toString()))
//				.findFirst()
//				.orElse(null);
//
//		return existingRune;
//	}
	/**

	 Increases the quantity of the given Rune object in the player's inventory.

	 @param rune The Rune object to increase.
	 */
//	public void increaseRune(Rune rune){
//
//		Rune existingRune = this.getExistingRune();
//		// Note: it shouldn't be null because we instantiate new Rune everytime we instantiate a player
//		if (existingRune != null){
//			existingRune.increaseRune(rune);
//		}
//
//	}


	/**

	 Decreases the quantity of the given Rune object in the player's inventory.
	 @param rune The Rune object to decrease.
	 @return True if the Rune was successfully decreased, false otherwise.
	 */
//	public boolean decreaseRune(Rune rune){
//		Rune existingRune = getExistingRune();
//		return existingRune.decreaseRune(rune);
//	}


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
//		this.removeItemFromInventory(getExistingRune());
//		this.addItemToInventory(new Rune());
		if (this.getWeaponInventory().size() == 0){
			this.addWeaponToInventory(this.originalWeapon);
		}

		rm.run(map,false);

		map.moveActor(this, this.getRespawnLocation());

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

		runeManager.setDroppedRune(runeManager.getPlayerRune());
		runeManager.resetPlayerRune();
//		Rune droppedRune = this.getExistingRune();

		// ensure that rune can be dropped; note we don't need to toggle back this because when this rune is picked
		// up, its amount will be added to another new instance of Rune which is not portable
		runeManager.getDroppedRune().togglePortability();

		// register this rune as resettable
		runeManager.getDroppedRune().registerAsResettable();

		return runeManager.getDroppedRune();
	}

	public RemembranceOfTheGrafted getExistingRemembranceOfTheGrafted(){

		return (RemembranceOfTheGrafted) this.getItemInventory()
		.stream()
		.filter(item -> "Remembrance of the Grafted".equals(item.toString()))
		.findFirst()
		.orElse(null);
	}

	public boolean isRemovableOnPlayerRest() {
		return false;
	}

}



