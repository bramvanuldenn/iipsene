package shared;

public interface GameObserver {
    void update(boolean settingsVisible, boolean cardsVisible, boolean state);
}
