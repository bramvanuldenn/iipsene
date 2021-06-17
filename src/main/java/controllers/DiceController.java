package controllers;

import models.Dice;
import views.DiceObserver;
/* Dice controller gemaakt
 * author: Hayyan Mezher
 */
public class DiceController {
    static DiceController diceController;
    final Dice dice;

    public DiceController() {
        this.dice = new Dice();
    }

    public static DiceController getInstance(){
        if (diceController == null){
            diceController = new DiceController();
        }
        return diceController;
    }

    public void setDiceController(){
        this.dice.rolDice();
    }

    public void subscribersController(DiceObserver diceObserver) {
        this.dice.registerObserver(diceObserver);
    }

}