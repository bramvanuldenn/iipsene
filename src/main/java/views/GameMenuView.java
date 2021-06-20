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
import models.GameMenu;
import models.User;
import shared.CountryObserver;
import shared.DiceObserver;
import shared.GameObserver;
import shared.UserObserver;

import java.util.Objects;

public class GameMenuView implements GameObserver, CountryObserver, DiceObserver, UserObserver {
    GameMenuController gameMenuController;
    GameMenu gameMenu;

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
    protected void initialize() throws Exception {
        //INIT CONTROLLER
        this.gameMenuController = GameMenuController.getInstance();

        //INIT OBSERVERS
        this.gameMenuController.registerGameObserver(this);
        this.gameMenuController.registerCountryObserver(this);
        this.gameMenuController.registerDiceObserver(this);
        this.gameMenuController.registerUserObserver(this);

        //CREATE ALL USER OBJECTS
        //TODO

        //INIT CANVAS AND DRAW COUNTRIES
        gc = gameCanvas.getGraphicsContext2D();
        updateCanvas();

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
        if (country.isCountrySelected()) {
            gc.setFill(Color.LIGHTYELLOW);
        } else {
            gc.setFill(Color.BLACK);
        }
        gc.fillRect(country.getCountryX()-1, country.getCountryY()-1, country.getWidth()+2, country.getHeight()+2);
        if (country.getCountryOwner() == null) {
            System.out.println(country.getCountryName() + " has no owner");
            gc.setFill(country.getColor());
        } else {
            System.out.println(country.getCountryName() + " becomes " + country.getCountryOwner().getColor().toString());
            gc.setFill(country.getCountryOwner().getColor());
        }
        gc.fillRect(country.getCountryX(), country.getCountryY(), country.getWidth(), country.getHeight());
    }

    // CALL DEZE FUNCTIE ELKE KEER NADAT JE EEN COUNTRY HEBT GEUPDATE, DIT RERENDERT DE HELE SCENE.
    public void updateCanvas() {
        gc.setFill(Color.BLUE);
        gc.fillRect(0,0,930,580);
        for (int i = 0; i < gameMenuController.getCountries().size(); i++) {
            gameMenuController.drawCountry(gameMenuController.getCountries().get(i));
        }
    }

    public void displayDiceRoll(int rolNo){
        this.diceRollLabel.setText(String.valueOf(rolNo));
    }

    @Override
    public void update(boolean settingsVisState, boolean cardsVisState, boolean playerVisState) {
        setSettingsVis(settingsVisState);
        setCardsVis(cardsVisState);
        setPlayerVis(playerVisState);
        updateCanvas();
    }

    @Override
    public void update(Country country) {
        drawCountry(country);
    }

    @Override
    public void update(int rolNo) {
        this.displayDiceRoll(rolNo);
    }

    @Override
    public void update(User user) {
        update(user);
    }
}