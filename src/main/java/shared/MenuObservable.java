package shared;

public interface MenuObservable {
    void register(MenuObserver observer);
    void notifyAllObservers();
    void setGameMenu();
}
