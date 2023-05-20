package game.items;

import game.reset.ResetManager;

public class RuneManager {
    private static RuneManager instance;


    private RuneManager(){};



    /**

     Returns the instance of this reset manager. If it doesn't exist yet, it creates a new instance and returns it.
     @return the instance of this reset manager
     */
    public static RuneManager getInstance(){
        if (instance == null){
            instance = new RuneManager();
        }
        return instance;
    }






}
