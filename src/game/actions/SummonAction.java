package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.gameactors.StatusActor;
import game.gameactors.allyorinvader.Ally;
import game.gameactors.allyorinvader.Invader;
import game.gameactors.players.*;
import game.utils.RandomNumberGenerator;
import game.weapons.Club;
import game.weapons.GreatKnife;
import game.weapons.Uchigatana;

import java.nio.file.StandardCopyOption;


/**
 * The SummonAction class represents an action that allows an actor to summon allies or invaders in a game.
 * It extends the Action class.
 *
 * @see Action
 * @version 1.0
 */
public class SummonAction extends Action {


    /**
     * Spawns an ally at the specified destination on the game map.
     *
     * @param map         the GameMap object representing the game map
     * @param destination the Location object representing the destination where the ally will be spawned
     * @return a String indicating the result of the spawn action
     * @see GameMap
     * @see Location
     */
    public void spawnAlly(GameMap map, Location destination){
//        Player player;
        int allyType = RandomNumberGenerator.getRandomInt(4);

        switch (allyType) {
            case 0:
//                player = new Astrologer();
                map.addActor(new Ally(Astrologer.getMaxHP(),new Uchigatana()), destination);
                break;
            case 1:
//                player = new Bandit();
                map.addActor(new Ally(Bandit.getMaxHP(),new GreatKnife()), destination);
                break;
            case 2:
//                player = new Samurai();
                map.addActor(new Ally(Samurai.getMaxHP(), new Uchigatana()), destination);
                break;
            case 3:
//                player = new Wretch();
                map.addActor(new Ally(Wretch.getMaxHP(),new Club()), destination);
                break;
        }
    }



    /**
     * Spawns an invader at the specified destination on the game map.
     *
     * @param map         the GameMap object representing the game map
     * @param destination the Location object representing the destination where the invader will be spawned
     * @return a String indicating the result of the spawn action
     * @see GameMap
     * @see Location
     */
    public void spawnInvader(GameMap map, Location destination){
//        Player player;
        int enemyType = RandomNumberGenerator.getRandomInt(4);

        switch (enemyType) {
            case 0:
//                player = new Astrologer();
                map.addActor(new Invader(Astrologer.getMaxHP(),new Uchigatana()), destination);
                break;
            case 1:
//                player = new Bandit();
                map.addActor(new Invader(Bandit.getMaxHP(),new GreatKnife()), destination);
                break;
            case 2:
//                player = new Samurai();
                map.addActor(new Invader(Samurai.getMaxHP(), new Uchigatana()), destination);
                break;
            case 3:
//                player = new Wretch();
                map.addActor(new Invader(Wretch.getMaxHP(),new Club()), destination);
                break;
        }
    }

    /**
     * Executes the summon action by spawning an ally or invader at a random exit location on the game map.
     *
     * @param actor the Actor object performing the summon action
     * @param map   the GameMap object representing the game map
     * @return a String indicating the result of the summon action
     * @see Actor
     * @see GameMap
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String res = "";
        for (Exit exit : map.locationOf(actor).getExits()) {
            if (!map.isAnActorAt(exit.getDestination())) {
                Location destination = exit.getDestination();
                // ally if 0 else invader
                if (RandomNumberGenerator.getRandomInt(2) == 0) {
                    res = "Ally has been summoned";
                    spawnAlly(map, destination);
                    return res;
                } else {
                    res = "Invader has been summoned";
                    spawnInvader(map, destination);
                    return res;
                }
            }
        }
        return res;
    }


    /**
     * Returns a description of the menu option for summoning a guest from another realm.
     *
     * @param actor The actor performing the summoning action.
     * @return A string description of the menu option.
     */
    @Override
    public String menuDescription(Actor actor) {
        return  "Summon guest from another realm";
    }
}
