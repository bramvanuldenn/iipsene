package shared;

/* Dice observable gemaakt
 * author: Hayyan Mezher
 */
public interface DiceObservable {
    public void registerObserver(DiceObserver observer);
    public void notifyAllObservers();
}