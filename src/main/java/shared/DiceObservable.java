package shared;

import views.DiceObserver;
/* Dice observable gemaakt
 * author: Hayyan Mezher
 */
public interface DiceObservable {
    public void registerObserver(DiceObserver observer);
    public void notifyAllObservers();
}