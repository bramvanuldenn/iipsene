package views;

import controllers.GameMenuController;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import models.Country;
import shared.CountryObserver;
import shared.DiceObserver;
import shared.GameObserver;

public class GameMenuView implements GameObserver, CountryObserver, DiceObserver {
    GameMenuController gameMenuController;

    @FXML
    ImageView settingsButton;
    @FXML
    Canvas gameCanvas;
    @FXML
    private Label diceRollLabel;

    private GraphicsContext gc;

    @FXML
    protected void initialize() {
        //INIT CONTROLLER
        this.gameMenuController = GameMenuController.getInstance();
        //INIT OBSERVERS
        this.gameMenuController.registerGameObserver(this);
        this.gameMenuController.registerCountryObserver(this);
        this.gameMenuController.registerDiceObserver(this);
        //INIT CANVAS
        gc = gameCanvas.getGraphicsContext2D();
        for (int i = 0; i < gameMenuController.getCountries().size(); i++) {
            gameMenuController.drawCountry(gameMenuController.getCountries().get(i));
        }
        //INIT OTHER WINDOWS
    }

    public void settingsButtonPressed(MouseEvent event) {
        gameMenuController.settingsPressed();
        System.out.println("pressed settings");
    }

    public void canvasClicked(MouseEvent event) {
        gameMenuController.canvasClicked(event);
    }

    public void rollDice(MouseEvent event) {
        gameMenuController.rollDice();
    }

    public void setSettingsVis(boolean state) {
        if (state) {
            settingsButton.setOpacity(0.0);
        } else {
            settingsButton.setOpacity(1.0);
        }
    }

    public void drawCountry(Country country) {
        gc.setFill(Color.BLACK);
        gc.fillRect(country.getCountryX()-1, country.getCountryY()-1, country.getWidth()+3, country.getHeight()+3);
        gc.setFill(country.getColor());
        gc.fillRect(country.getCountryX(), country.getCountryY(), country.getWidth(), country.getHeight());
    }

    public void displayDiceRoll(int rolNo){
        this.diceRollLabel.setText(String.valueOf(rolNo));
    }

    @Override
    public void update(boolean state) {
        setSettingsVis(state);
    }

    @Override
    public void update(Country country) {
        drawCountry(country);
    }

    @Override
    public void update(int rolNo) {
        this.displayDiceRoll(rolNo);
        System.out.println(rolNo);
    }
}