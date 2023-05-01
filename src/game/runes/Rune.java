package game.runes;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.items.RecoverRuneAction;

public class Rune extends Item {

    private int amount = 0;

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
}
