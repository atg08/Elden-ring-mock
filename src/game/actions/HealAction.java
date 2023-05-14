package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Healer;

public class HealAction extends Action {

    private final Healer healerItem;

    public HealAction(Healer healerItem){
        this.healerItem = healerItem;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (this.healerItem.isAvailable()){
            actor.heal(this.healerItem.getHealAmount());
            this.healerItem.updateStatus();
            return "The actors health has been restored by " + this.healerItem.getHealAmount();
        }
        return "The healer item was not available";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes the " + this.healerItem;
    }
}
