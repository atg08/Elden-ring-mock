package game.Reset;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.runes.Rune;

import java.util.ArrayList;
import java.util.List;

/**
 * A reset manager class that manages a list of resettables.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class ResetManager {
    private List<Resettable> resettables;
    private List<Resettable> removables = new ArrayList<>();
    private List<Resettable> resettablesToBeAddedAgain = new ArrayList<>();
    private static ResetManager instance;

    /**
     * HINT 1: where have we seen a private constructor before?
     * HINT 2: see the instance attribute above.
     */
    private ResetManager() {
        this.resettables = new ArrayList<>();
    }

    public void run(Actor actor, GameMap map) {

        for (Resettable r : this.resettables){
            if (r.isRemovable()){
                this.removables.add(r);
            }
//            r.reset(actor,map);
            String message = r.reset(actor,map);
            if (!message.equals("")){System.out.println(message);}
        }


        for (Resettable r2: this.removables){
            // in-case actor is removed
            this.removeResettable(r2);
        }

        this.resettables.addAll(this.resettablesToBeAddedAgain);
        this.resettablesToBeAddedAgain.clear();

    }




    public void registerResettable(Resettable resettable) {
        this.resettables.add(resettable);
    }

    public void removeResettable(Resettable resettable) {
        this.resettables.remove(resettable);
    }

    public void registerAsResettableAgain(Resettable resettable){this.resettablesToBeAddedAgain.add(resettable);}

//    public void registerRemovable(Resettable resettable) {
//        this.removables.add(resettable);
//    }
//
//    public void removeRemovable(Resettable resettable) {
//        this.removables.remove(resettable);
//    }
    public static ResetManager getInstance(){
        if (instance == null){
            instance = new ResetManager();
        }
        return instance;
    }


}
