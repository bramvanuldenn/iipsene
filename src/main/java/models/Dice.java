package models;

import shared.DiceObservable;
import shared.DiceObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/* Dice module gemaakt
 * author: Hayyan Mezher
 */
public class Dice implements DiceObservable {

    final int[] diceCol = {1, 2, 3, 4, 5, 6};
    int rolNo; //Roll number aan de dice wanneer hij rolt.
    private List<DiceObserver> diceSubscribers = new ArrayList<>();

    public void rolDice() {
        this.rolNo = diceCol[new Random().nextInt(diceCol.length)];
        notifyAllObservers();
    }

    @Override
    public void registerObserver(DiceObserver observer) {
        this.diceSubscribers.add(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (DiceObserver observer: this.diceSubscribers){
            observer.update(rolNo);

        }
    }
}