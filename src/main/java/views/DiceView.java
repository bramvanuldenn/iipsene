package views;

import controllers.DiceController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
/* Dice view gemaakt
 * author: Hayyan Mezher
 */
public class DiceView implements DiceObserver {

    DiceController diceController;

    @FXML
    private Label diceRollLabel;

    @FXML
    protected void initialize() {
        this.diceController = diceController.getInstance();
        this.diceController.subscribersController(this);
    }

    @FXML
    public void rolDice(){
        diceController.setDiceController();
    }

    public void displayDiceRoll(int rolNo) {
        this.diceRollLabel.setText(String.valueOf(rolNo));
    }

    @Override
    public void update(int rolNo) {
        this.displayDiceRoll(rolNo);
    }
}
