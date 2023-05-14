package game.items;

public interface Healer {
    boolean isAvailable();
    int getHealAmount();
    void updateStatus();
}
