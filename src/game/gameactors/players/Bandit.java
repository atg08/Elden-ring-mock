package game.gameactors.players;

public class Bandit extends Player{
    /**
     * Constructor.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Bandit(String name, char displayChar, int hitPoints) {
        super( 414);

        // if transaction successful
        addWeaponToInventory(new GreatKnife());
    }
}
