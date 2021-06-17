package shared;

public interface MenuObservable {
    public void register(MenuObserver observer);
    public void notifyAllObservers();
    public void setGameMenu();
}
