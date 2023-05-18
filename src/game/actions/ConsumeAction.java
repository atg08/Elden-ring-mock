package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Consumable;

public class ConsumeAction extends Action {

    private Consumable consumable;

    public ConsumeAction(Consumable _consumable) {
        this.consumable = _consumable;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return consumable.consumeBy(actor) ? consumable.consume(actor) : actor + " does nothing";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " can consume " + consumable.toString();
    }
}
