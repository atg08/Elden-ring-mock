package game.gameactors.playeroptions;

public class Samurai extends Player{
    /**
     * Constructor.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Samurai(String name, char displayChar, int hitPoints) {
        super("Tarnished", '@', 455);
        //addWeaponToInventory(new Uchigatana());
    }

}