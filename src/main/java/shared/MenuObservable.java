package shared;

public interface MenuObservable {
    public static void register(MenuObserver observer) {

    }

    public void notifyAllObservers();
    public void setGameMenu();
}
