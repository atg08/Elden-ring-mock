package game;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.gameactors.enemies.wind.LoneWolf;
import game.gameactors.players.Bandit;
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

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor());

		List<String> map = Arrays.asList(
				"...nnnn....................................................................",
				".......nnn............#####....######.........................bbbbbb.......",
				"......................#..___....____#...............................bbbb...",
				"...nn..nn....nn...................__#......................................",
				"......................._____........#..................bb...bb....bbbb.....",
				"........nnnn..........#............_#......................................",
				"....&&................#...........###......................................",
				".......&&..................................................................",
				".......................................................GG.....GGGG.........",
				"..&&&&............................###___###.......................GGGG.....",
				"..................................________#...............GGGGGG...........",
				"..~~..~~..~~......................#________................................",
				"..................................#_______#................................",
				"..........~~......................###___###................................",
				"......~~............................#___#..................................",
				"...~~......................................................................",
				"...........................................................................",
				"..................................................RRRR.....RR...RRRR.......",
				"..####__##..........####____...........................RRR....######..##...",
				"..#.....__........................................RR...RR.RR..#....____....",
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

		gameMap.at(23, 17).addActor(new LoneWolf());

		// HINT: what does it mean to prefer composition to inheritance?
		// do this in the character type the player chooses
//		Player player = new Player("Tarnished", '@', 300);
//		world.addPlayer(player, gameMap.at(36, 10));


		Scanner sel = new Scanner(System.in);

		System.out.println("Select you role:");
		System.out.println("b: Bandit");
		System.out.println("s: Samurai");
		System.out.println("w: Wretch");
		String choice = sel.nextLine();

		String selection;
		do {
			selection = choice;
			switch (selection) {
				case "b":
					Bandit bandit = new Bandit();
					break;
				case "s":
					Samurai samurai = new Samurai();
					break;
				case "w":
					Wretch wretch = new Wretch();
			}
		} while (selection != "x");

		world.run();

	}
}
