package shared;

import views.DiceObserver;
/* Dice observable gemaakt
 * author: Hayyan Mezher
 */
public interface DiceObservable {
    void registerObserver(DiceObserver observer);

    void notifyObservers();
}