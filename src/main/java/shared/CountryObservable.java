package shared;

/**
 *
 */
public interface CountryObservable {
    void register(CountryObserver observer);
    void notifyAllObservers();
}
