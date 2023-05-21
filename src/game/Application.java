package game;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actions.TeleportAction;
import game.doors.GoldenFogDoor;
import game.environments.*;
import game.environments.siteoflostgrace.StormveilMainGate;
import game.environments.siteoflostgrace.TableOfLostGrace;
import game.environments.siteoflostgrace.TheFirstStep;
import game.gameactors.FingerReaderEnia;
import game.gameactors.MerchantKale;
import game.gameactors.enemies.NPC;
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
				new GustOfWind(), TheFirstStep.getInstance(), new Cliff(),new GoldenFogDoor(), new SummonSign() ,
				new FlareOfInferno() , new TrevorOfEarth());


		// don't put enemy in the map; they will be spawned automatically
		// note: if you don't have SiteOfLostGrace it gives you an error
//		List<String> limgrave  = Arrays.asList(
//				"......................#....._D_..=..#..........................+++.........",
//				"......................#.....___.....#.......................+++++..........",
//				"......................#..___....____#.........................+++++........",
//				"......................#...........__#............................++........",
//				"......................#_____........#.............................+++......",
//				"......................#............_#..............................+++.....",
//				"......................######...######......................................",
//				"...........................................................................",
//				"...........................................................................",
//				"........++++......................###___###................................",
//				"........+++++++...................________#................................",
//				"..........+++.....................#________................................",
//				"............+++...................#___U___#................................",
//				".............+....................###___###................................",
//				"............++......................#___#..................................",
//				"..............+............................................................",
//				"..............++...........................................................",
//				"..............................................++...........................",
//				"..................++++......................+++...............######..##...",
//				"#####___######....++...........................+++............#....____....",
//				"_____________#.....++++..........................+..............__.....#...",
//				"_____D_______#.....+....++........................++.........._.....__.#...",
//				"_____________#.........+..+.....................+++...........###..__###...",
//				"_____________#.............++..............................................");

		List<String> limgrave  = Arrays.asList(
				"......................#....._D_..=..#..........................+++.........",
				"......................#.....___.....#.......................+++++..........",
				"......................#..___....____#.........................+++++........",
				".............=........#...........__#............................++........",
				"......................#_____........#.............................+++......",
				"......................#............_#..............................+++.....",
				"......................######...######......................................",
				"...........................................................................",
				"...........................................................................",
				"........++++......................###___###................................",
				"........+++++++...................________#................................",
				"..........+++.....................#________+...............................",
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
		GameMap limgraveMap = new GameMap(groundFactory, limgrave);
//		gameMap.at(5,21).setGround(new GoldenFogDoor());

		FancyGroundFactory groundFactory2 = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(),
				new GoldenFogDoor(), new Cliff(), new Cage(), new Barrack(), StormveilMainGate.getInstance(),
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
		GameMap stormveilCastleMap = new GameMap(groundFactory2, stormveilCastle);
		stormveilCastleMap.at(31,21).addItem(new GoldenRune());
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
		GameMap roundtableHoldMap = new GameMap(groundFactory3, roundtableHold);
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
		GameMap bossRoomMap = new GameMap(groundFactory4, bossRoom);

		world.addGameMap(limgraveMap);
		world.addGameMap(stormveilCastleMap);
		world.addGameMap(roundtableHoldMap);
		world.addGameMap(bossRoomMap);

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
		FingerReaderEnia fre = new FingerReaderEnia();

		world.addPlayer(player, limgraveMap.at(13, 4));
		world.addPlayer(kale, limgraveMap.at(40, 12));
		world.addPlayer(fre, roundtableHoldMap.at(5, 5));
		NPC.addPlayer(player);
//		world.addPlayer(player, gameMap.at(29, 0));

		// since access to maps only in application better to get the values here
		// door in Roundtable to door to limgrave
		TeleportAction.addFogDoorLocation("ROUNDTABLE_LIMGRAVE",limgraveMap.at(5,21));
		// door in limgrave to door to Roundtable
		TeleportAction.addFogDoorLocation("LIMGRAVE_ROUNDTABLE",roundtableHoldMap.at(9,10));
		// door in Stormveil to door to limgrave
		TeleportAction.addFogDoorLocation("STORMVEIL_LIMGRAVE",limgraveMap.at(29, 0));
		// door in limgrave to door to Stormveil
		TeleportAction.addFogDoorLocation("LIMGRAVE_STORMVEIL",stormveilCastleMap.at(38, 23));
		// door in Stormveil to door to Bossroom
		TeleportAction.addFogDoorLocation("STORMVEIL_BOSSROOM",bossRoomMap.at(0,3));

		Player.setRespawnLocation(limgraveMap.at(38,12)); // set default spawn location to TheFirstStep


		world.run();

	}
}
