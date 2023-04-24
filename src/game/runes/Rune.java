package game.runes;

import edu.monash.fit2099.engine.items.Item;

public class Rune extends Item {

    private int amount = 0;

    /***
     * Constructor.
     */
    public Rune() {
        super("rune", '$', true);
    }

    public Rune(int amount){
        super("rune", '$', true);
        this.amount = amount;

    }

    public void increaseRune(Rune rune){
        this.amount += rune.getAmount();
    }

    public int getAmount() {
        return this.amount;
    }

    public boolean decreaseRune(Rune rune){
        if (this.amount - rune.getAmount() < 0){
            this.amount -= rune.getAmount();
            return true;
        }else{
            return false;
        }
    }
}
