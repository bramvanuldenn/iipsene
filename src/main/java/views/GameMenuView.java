package views;

import controllers.GameMenuController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.Country;
import shared.CountryObserver;
import shared.DiceObserver;
import shared.GameObserver;

import java.util.Objects;

public class GameMenuView implements GameObserver, CountryObserver, DiceObserver {
    GameMenuController gameMenuController;

    @FXML
    ImageView settingsButton;
    @FXML
    ImageView cardsButton;
    @FXML
    ImageView playerButton;
    @FXML
    Canvas gameCanvas;
    @FXML
    private Label diceRollLabel;

    private GraphicsContext gc;

    private Stage settingsWindow;
    private Stage cardsWindow;
    private Stage playerWindow;

    @FXML
    protected void initialize() {
        //INIT CONTROLLER
        this.gameMenuController = GameMenuController.getInstance();

        //INIT OBSERVERS
        this.gameMenuController.registerGameObserver(this);
        this.gameMenuController.registerCountryObserver(this);
        this.gameMenuController.registerDiceObserver(this);

        //INIT CANVAS AND DRAW COUNTRIES
        gc = gameCanvas.getGraphicsContext2D();
        for (int i = 0; i < gameMenuController.getCountries().size(); i++) {
            gameMenuController.drawCountry(gameMenuController.getCountries().get(i));
        }

        //INIT OTHER WINDOWS
        settingsWindow = createWindow("settingsMenu.fxml", "Settings");
        settingsWindow.setOnCloseRequest(e -> {
            e.consume();
            gameMenuController.settingsPressed();
        });
        cardsWindow = createWindow("cardsMenu.fxml", "Cards");
        cardsWindow.setOnCloseRequest(e -> {
            e.consume();
            gameMenuController.cardsPressed();
        });
        playerWindow = createWindow("playerMenu.fxml", "Players");
        playerWindow.setOnCloseRequest(e -> {
            e.consume();
            gameMenuController.playerPressed();
        });
    }

    public Stage createWindow(String windowFile, String windowName) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(windowFile)));
            Stage stage = new Stage();
            stage.setTitle(windowName);
            stage.setScene(new Scene(root));
            return stage;
        } catch (Exception e) {
            System.out.println("Error loading window " + windowFile + "due to: " + e);
            return null;
        }
    }

    //EVENT HANDLERS

    public void settingsButtonPressed() {
        gameMenuController.settingsPressed();
    }

    public void cardsButtonPressed() {
        gameMenuController.cardsPressed();
    }

    public void playerButtonPressed() {
        gameMenuController.playerPressed();
    }

    public void canvasClicked(MouseEvent event) {
        gameMenuController.canvasClicked(event);
    }

    public void rollDice() {
        gameMenuController.rollDice();
    }


    //HANDLE DRAWING
    public void setSettingsVis(boolean state) {
        if (state) {
            settingsWindow.hide();
            settingsButton.setOpacity(1.0);
        } else {
            settingsWindow.show();
            settingsButton.setOpacity(0.0);
        }
    }

    public void setCardsVis(boolean state) {
        if (state) {
            cardsWindow.hide();
            cardsButton.setOpacity(1.0);
        } else {
            cardsWindow.show();
            cardsButton.setOpacity(0.0);
        }
    }

    public void setPlayerVis(boolean state) {
        if (state) {
            playerWindow.hide();
            playerButton.setOpacity(1.0);
        } else {
            playerWindow.show();
            playerButton.setOpacity(0.0);
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
    public void update(boolean settingsVisState, boolean cardsVisState, boolean playerVisState) {
        setSettingsVis(settingsVisState);
        setCardsVis(cardsVisState);
        setPlayerVis(playerVisState);
    }

    @Override
    public void update(Country country) {
        drawCountry(country);
    }

    @Override
    public void update(int rolNo) {
        this.displayDiceRoll(rolNo);
    }
}