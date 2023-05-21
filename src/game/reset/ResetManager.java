package game.reset;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.ArrayList;
import java.util.List;

/**

 * A reset manager class that manages a list of {@code Resettable} objects. This class allows the registration of
 * {@code Resettable} objects, which can be reset when needed. It also provides methods to remove or re-add
 * {@code Resettable} objects.
 * This class implements the singleton pattern, so there can only be one instance of it in the system.
 * Created by: Adrian Kristanto
 * Modified by: Tanul, Satoshi, Aditti
 * @version 1.0
 */
public class ResetManager {
    private List<Resettable> resettables;
    private List<Resettable> removables = new ArrayList<>();
    private List<Resettable> resettablesToBeAddedAgain = new ArrayList<>();
    private static ResetManager instance;
    private final Display display = new Display();

    /**

     Constructs a new {@code ResetManager} object with an empty list of resettables.
     */
    private ResetManager() {
        this.resettables = new ArrayList<>();
    }

    /**

     Runs the reset process for all registered resettables. It removes resettables marked as removable, resets all

     other resettables, and adds resettables that were marked as removable and need to be added back.
     * @param map the game map where the resettables reside

     */
    public void run(GameMap map, Boolean rest) {

        for (Resettable r : this.resettables){
            if (r.isRemovable() && rest && r.isRemovableOnPlayerRest()){
                this.removables.add(r);
            } else if (r.isRemovable() && !rest) {
                this.removables.add(r);
            }
//            r.reset(actor,map);
            String message = r.reset(map, rest);
            if (!message.equals("")){display.println(message);}
        }


        for (Resettable r2: this.removables){
            // in-case actor is removed
            this.removeResettable(r2);
        }

        this.resettables.addAll(this.resettablesToBeAddedAgain);
        this.resettablesToBeAddedAgain.clear();

    }



    /**

     Registers a {@code Resettable} object to be managed by this reset manager.
     @param resettable the {@code Resettable} object to be registered
     */
    public void registerResettable(Resettable resettable) {
        this.resettables.add(resettable);
    }

    /**

     Removes a {@code Resettable} object from the list of resettables managed by this reset manager.
     @param resettable the {@code Resettable} object to be removed
     */
    public void removeResettable(Resettable resettable) {
        this.resettables.remove(resettable);
    }

    /**

     Registers a {@code Resettable} object that was previously removed to be managed again by this reset manager.
     @param resettable the {@code Resettable} object to be registered again
     */
    public void registerAsResettableAgain(Resettable resettable){this.resettablesToBeAddedAgain.add(resettable);}

    /**

     Returns the instance of this reset manager. If it doesn't exist yet, it creates a new instance and returns it.
     @return the instance of this reset manager
     */
    public static ResetManager getInstance(){
        if (instance == null){
            instance = new ResetManager();
        }
        return instance;
    }


}
