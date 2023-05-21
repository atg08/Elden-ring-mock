package game.items;


/**
 * The Healer interface represents a healer in a game.
 *
 * @version 1.0
 */
public interface Healer {

    /**
     * Checks if the healer is available.
     *
     * @return true if the healer is available, false otherwise
     */
    boolean isAvailable();

    /**
     * Gets the amount of healing the healer can provide.
     *
     * @return the amount of healing
     */
    int getHealAmount();

    /**
     * Updates the status of the healer.
     *
     * @see Player#updateStatus()
     */
    void updateStatus();
}
