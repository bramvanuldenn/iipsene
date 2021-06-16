package shared;

public interface GameObservable {
    public void register(GameObserver observer);
    public void notifyAllObservers();
    public void setSettingsVis(boolean state);
}
