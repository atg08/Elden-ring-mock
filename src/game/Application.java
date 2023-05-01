package game;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.environments.*;
import game.gameactors.enemies.Enemy;
import game.gameactors.players.Bandit;
import game.gameactors.players.Player;
import game.gameactors.players.Samurai;
import game.gameactors.players.Wretch;
import game.grounds.Dirt;
import game.grounds.Floor;
import game.grounds.Wall;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */

public class Application {

	public static void main(String[] args) {

		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(),
				new Wall(), new Floor(),
				new Graveyard(), new PuddleOfWater(),
				new GustOfWind(), TheFirstStep.getInstance());

		// don't put enemy in the map; they will be spawned automatically
		List<String> map = Arrays.asList(
				"...nnnn....................................................................",
				".......nnn............#####....######.........................&&&&&&.......",
				"......................#..___....____#...............................&&&&...",
				"...nn..nn....nn...................__#......................................",
				"......................._____........#..................&&...&&....&&&&.....",
				"........nnnn..........#............_#......................................",
				"....&&................#...........###......................................",
				".......&&..................................................................",
				".......................................................~~.....~~~~.........",
				"..&&&&............................###___###.......................~~~~.....",
				"..................................________#...............~~~~~~...........",
				"..~~..~~..~~......................#___U____................................",
				"..................................#_______#................................",
				"..........~~......................###___###................................",
				"......~~............................#___#..................................",
				"...~~......................................................................",
				"...........................................................................",
				"..................................................nnnn.....nn...nnnn.......",
				"..####__##..........####____...........................nnn....######..##...",
				"..#.....__........................................nn...nn.nn..#....____....",
				"..#___..........##..........#________...........................__.....#...",
				"..####__###..................................................._.....__.#...",
				"..............................................................###..__###...",
				"......................_________####........................................");
		GameMap gameMap = new GameMap(groundFactory, map);
		world.addGameMap(gameMap);


		// BEHOLD, ELDEN RING
		for (String line : FancyMessage.ELDEN_RING.split("\n")) {
			new Display().println(line);
			try {
				Thread.sleep(200);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		Scanner sel = new Scanner(System.in);

		System.out.println("Select you role:");
		System.out.println("b: Bandit");
		System.out.println("s: Samurai");
		System.out.println("w: Wretch");
		String choice = sel.nextLine();

		Player player = null;
		String selection;

		// TODO use Menu class
		do {
			selection = choice;
			switch (selection) {
				case "b":
					player = new Bandit();
					break;
				case "s":
					player = new Samurai();
					break;
				case "w":
					player = new Wretch();
					break;
			}
		} while (player == null);

		world.addPlayer(player, gameMap.at(36, 10));

		// add available behaviours to enemy
		Enemy.addBehaviourWithPriority(new WanderBehaviour(), 3);
		Enemy.addBehaviourWithPriority(new AttackBehaviour(player), 1);
		Enemy.addBehaviourWithPriority(new FollowBehaviour(player), 2);


		world.run();

	}
}
