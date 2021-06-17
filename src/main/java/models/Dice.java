package models;

import shared.DiceObservable;
import views.DiceObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/* Dice module gemaakt
 * author: Hayyan Mezher
 */
public class Dice implements DiceObservable {

    final int[] diceCol = {1, 2, 3, 4, 5, 6};
    int rolNo; //Roll number aan de dice wanneer hij rolt.
    final Random random = new Random();
    private List<DiceObserver> diceSubscribers = new ArrayList<>();

    public int[] getDiceCol() {
        return diceCol;
    }

    public void rolDice() {
        for (int i = 0; i < diceCol.length; i++) {
            diceCol[i] = random.nextInt();
            this.rolNo = getDiceCol()[i];
            System.out.println(rolNo);
        }
    }

    @Override
    public void registerObserver(DiceObserver observer) {
        this.diceSubscribers.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (DiceObserver observer: this.diceSubscribers){
            observer.update(rolNo);
        }
    }
}