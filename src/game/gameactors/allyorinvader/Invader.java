package game.gameactors.allyorinvader;

import edu.monash.fit2099.demo.conwayslife.Status;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;
import game.gameactors.StatusActor;
import game.gameactors.enemies.DeathRuneDroppper;
import game.gameactors.enemies.Enemy;
import game.gameactors.enemies.IFollowable;
import game.gameactors.enemies.NPC;
import game.gameactors.players.Player;
import game.items.Rune;
import game.utils.RandomNumberGenerator;

public class Invader extends NPC implements DeathRuneDroppper, IFollowable {
    private int minDeathRuneAmount = 1358;
    private int maxDeathRuneAmount = 5578;
    private Location previousLocation;

    /**
     * Constructor for the Enemy class.
     */
    public Invader(Player player) {
        super("invader", 'ඞ', player.getMaxHP());
        this.addCapability(StatusActor.HOSTILE_TO_PLAYER);
        this.addCapability(StatusActor.IS_DEATH_RUNE_DROPPER);
        this.addCapability(StatusActor.IS_INVADER);
        addWeaponToInventory(player.getWeaponInventory().get(0));

        this.behaviours.put(1, new AttackBehaviour());
        this.behaviours.put(3, new WanderBehaviour());


    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display){
        // record invader's current location
        this.previousLocation = map.locationOf(this);

        return super.playTurn(actions, lastAction, map, display);
    }

    @Override
    public boolean canTarget(Actor subject) {
        return !subject.hasCapability(StatusActor.IS_INVADER);
    }

    @Override
    public Rune getDeathRune() {
        return new Rune(RandomNumberGenerator.getRandomIntInRange(this.minDeathRuneAmount, this.maxDeathRuneAmount));
    }

    /**

     Retrieves the player's previous location.
     @return The player's previous location.
     */
    @Override
    public Location getPlayerPreviousLocation(){
        return this.previousLocation;
    }
}
