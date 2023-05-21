package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import game.items.Rune;

public interface Sellable {
    Rune getSellingPrice();
    boolean isSellableToAnActor(Actor actor);
}
