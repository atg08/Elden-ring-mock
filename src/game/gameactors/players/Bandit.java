package game.gameactors.players;

public class Bandit extends Player{

    /**
     * Constructor.
     *
     * @param name      Name to call the player in the UI
     * @param hitPoints Player's starting number of hitpoints
     */
    public Bandit(String name, int hitPoints) {
        super("Bandit", 414);
        this.addWeaponToInventory(new GreatKnife());
    }
}
