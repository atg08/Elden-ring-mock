package game.runes;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.items.RecoverRuneAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Reset.ResetManager;
import game.Reset.Resettable;

public class Rune extends Item implements Resettable{

    private int amount = 0;
    private Location runeLocation = null;
    private ResetManager rm = ResetManager.getInstance();
    private final int MAX_RESET_COUNT_FOR_REMOVAL = 2;
    private int reset_attempts = 0;

    /***
     * Constructor.
     */
    public Rune() {
        super("rune", '$', false);
    }

    public Rune(int amount){
        super("rune", '$', false);
        this.amount = amount;

    }

    public void increaseRune(Rune rune){
        this.amount += rune.getAmount();
    }

    public int getAmount() {
        return this.amount;
    }

    public boolean decreaseRune(Rune rune){
        if (this.amount - rune.getAmount() >= 0){
            this.amount -= rune.getAmount();
            return true;
        }else{
            return false;
        }
    }

    public PickUpAction getPickUpAction(Actor actor) {
        if(portable)
            return new RecoverRuneAction(this);
        return null;
    }

    public void setRuneLocation(Location runeLocation) {
        this.runeLocation = runeLocation;
    }

    public void registerAsResettable(){
        this.rm.registerResettable(this);
    }

    @Override
    public String reset(Actor actor, GameMap map) {
        if (this.checkForRemoval()){
            runeLocation.removeItem(this);
            return "rune removed from the map";
        }
        this.rm.registerAsResettableAgain(this);
        return "";
    }

    public boolean checkForRemoval(){
        this.reset_attempts += 1;
        return this.reset_attempts == this.MAX_RESET_COUNT_FOR_REMOVAL;
    }

    @Override
    public boolean isRemovable() {
        return true;
    }
}
