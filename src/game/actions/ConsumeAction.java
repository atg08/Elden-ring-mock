package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.FlaskOfCrimsonTears;
import game.items.ItemUsage;

public class ConsumeAction extends Action {

    private Item consumable;

    public ConsumeAction(Item _consumable){
        this.consumable = _consumable;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (consumable.hasCapability(ItemUsage.CAN_CONSUME_TO_HEAL)){ // separate if blocks for extensibility
            if (consumable.hasCapability(ItemUsage.IS_FLASK)){ // makes the below part more appropriate
                FlaskOfCrimsonTears flask = (FlaskOfCrimsonTears) consumable;
                if (flask.getMAX_CONSUME_AMOUNT() - flask.getConsumed() != 0){
                    actor.heal(flask.getHEAL_AMOUNT());
                    flask.updateConsumed();
                    return "The actors health has been restored by " + flask.getHEAL_AMOUNT();
                }
                else {
                    return new DoNothingAction().execute(actor,map);
                }
            }
        }
        return "nothing to consume";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes the " + consumable;
    }
}
