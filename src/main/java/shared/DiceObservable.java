package shared;

/* Dice observable gemaakt
 * author: Hayyan Mezher
 */
public interface DiceObservable {
    void registerObserver(DiceObserver observer);
    void notifyAllObservers();
}