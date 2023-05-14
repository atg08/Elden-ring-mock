package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.*;

import java.util.Random;

public class GoldenRune extends Item {

    boolean consumed = false;
    final int MAX_AMOUNT = 100000;
    final int MIN_AMOUNT = 200;

    /***
     * Constructor.
     */
    public GoldenRune() {
        super("Golden Rune", '*', true);
    }

    public boolean isAvailable(){
        return this.consumed;
    }

    public void updateStatus(){
        this.consumed = true;
    }

    public Rune getRuneAmount(){
        Random rand = new Random();
        return new Rune(rand.nextInt(this.MIN_AMOUNT, this.MAX_AMOUNT+1));
    }
}
