package game;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.doors.GoldenFogDoor;
import game.environments.*;
import game.gameactors.MerchantKale;
import game.gameactors.allyorinvader.Ally;
import game.gameactors.allyorinvader.Invader;
import game.gameactors.enemies.Enemy;
import game.gameactors.players.*;
import game.grounds.Cliff;
import game.grounds.Dirt;
import game.grounds.Floor;
import game.grounds.Wall;
import game.items.GoldenRune;

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
				new GustOfWind(), TheFirstStep.getInstance(), new Cliff(),new GoldenFogDoor(), new SummonSign());


		// don't put enemy in the map; they will be spawned automatically
		// note: if you don't have SiteOfLostGrace it gives you an error
		List<String> limgrave  = Arrays.asList(
				"......................#....._D_..=..#..........................+++.........",
				"......................#.....___.....#.......................+++++..........",
				"......................#..___....____#.........................+++++........",
				"......................#...........__#............................++........",
				"......................#_____........#.............................+++......",
				"......................#............_#..............................+++.....",
				"......................######...######......................................",
				"...........................................................................",
				"...........................................................................",
				"........++++......................###___###................................",
				"........+++++++...................________#................................",
				"..........+++.....................#________................................",
				"............+++...................#___U___#................................",
				".............+....................###___###................................",
				"............++......................#___#..................................",
				"..............+............................................................",
				"..............++...........................................................",
				"..............................................++...........................",
				"..................++++......................+++...............######..##...",
				"#####___######....++...........................+++............#....____....",
				"_____________#.....++++..........................+..............__.....#...",
				"_____D_______#.....+....++........................++.........._.....__.#...",
				"_____________#.........+..+.....................+++...........###..__###...",
				"_____________#.............++..............................................");
		GameMap gameMap = new GameMap(groundFactory, limgrave);
//		gameMap.at(5,21).setGround(new GoldenFogDoor());

		FancyGroundFactory groundFactory2 = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(),
				new GoldenFogDoor(), new Cliff(), new Cage(), new Barrack(),StormveilMainGate.getInstance(),
				new GustOfWind());
		List<String> stormveilCastle  = Arrays.asList(
				"...............D...........................................................",
				"..................<...............<........................................",
				"...........................................................................",
				"##############################################...##########################",
				"............................#................#.......B..............B......",
				".....B...............B......#................#.............................",
				"...............................<.........<.................................",
				".....B...............B......#................#.......B..............B......",
				"............................#................#.............................",
				"#####################..#############...############.####..#########...#####",
				"...............#++++++++++++#................#++++++++++++#................",
				"...............#++++++++++++...<.........<...#++++++++++++#................",
				"...............#++++++++++++..................++++++++++++#................",
				"...............#++++++++++++#................#++++++++++++#................",
				"#####...##########.....#############...#############..#############...#####",
				".._______........................B......B........................B.....B...",
				"_____..._..____....&&........<..............<..............................",
				".........____......&&......................................................",
				"...._______..................<..............<....................<.....<...",
				"#####....##...###..#####...##########___###############......##.....####...",
				"+++++++++++++++++++++++++++#..........U........#+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++....................#+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++#.........___........+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++#........._D_.......#+++++++++++++++++++++++++++");
		GameMap gameMap2 = new GameMap(groundFactory2, stormveilCastle);
		gameMap2.at(31,21).addItem(new GoldenRune());
//		37,38,39 -> x , y = 21,22

		FancyGroundFactory groundFactory3 = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(),
				 new GoldenFogDoor(), TableOfLostGrace.getInstance());
		List<String> roundtableHold  = Arrays.asList(
				"##################",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________U_______#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"########_D_#######");
		GameMap gameMap3 = new GameMap(groundFactory3, roundtableHold);
//		gameMap3.at(9,10).setGround(new GoldenFogDoor());


		FancyGroundFactory groundFactory4 = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(),
				new Cliff(),new SummonSign());
		List<String> bossRoom  = Arrays.asList(
				"+++++++++++++++++++++++++",
				".........................",
				"..=......................",
				".........................",
				".........................",
				".........................",
				".........................",
				".........................",
				"+++++++++++++++++++++++++");
		GameMap gameMap4 = new GameMap(groundFactory4, bossRoom);

		world.addGameMap(gameMap);
		world.addGameMap(gameMap2);
		world.addGameMap(gameMap3);
		world.addGameMap(gameMap4);


		Display display = new Display();

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

		display.println("Select you role:");
		display.println("a: Astrologer");
		display.println("b: Bandit");
		display.println("s: Samurai");
		display.println("w: Wretch");
		char choice = display.readChar();

		Player player = null;
		char selection;

		// TODO use Menu class
		do {
			selection = choice;
			switch (selection) {
				case 'a':
					player = new Astrologer();
					break;
				case 'b':
					player = new Bandit();
					break;
				case 's':
					player = new Samurai();
					break;
				case 'w':
					player = new Wretch();
					break;

			}
		} while (player == null);


		MerchantKale kale = new MerchantKale();

		world.addPlayer(player, gameMap2.at(30, 23));
//		world.addPlayer(kale, gameMap.at(40, 12));


		// adding the accessible maps to player
		Player.addMapAccessible(gameMap); // limgrave
		Player.addMapAccessible(gameMap2); // stormveil
		Player.addMapAccessible(gameMap3); // roundtable
		Player.addMapAccessible(gameMap4); // bossroom

		world.run();

	}
}
