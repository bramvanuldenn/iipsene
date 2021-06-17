package shared;

/**
 *
 */
public interface CountryObservable {
    public void register(CountryObserver observer);
    public void notifyAllObservers();
}
