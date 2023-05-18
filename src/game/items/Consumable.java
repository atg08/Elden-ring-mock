package game.items;

import edu.monash.fit2099.engine.actors.Actor;

public interface Consumable {

    String consume(Actor actor);

    Boolean consumeBy(Actor actor);
}
