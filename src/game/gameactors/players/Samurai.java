package game.gameactors.players;

public class Samurai extends Player{

    /**
     * Constructor.
     *
     * @param name        Name to call the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Samurai(String name) {
        super("Samurai", 455);
        this.addWeaponToInventory(new Uchigatana());
    }
}
