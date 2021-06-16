package views;

import controllers.GameMenuController;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

import models.GameMenu;
import shared.GameObservable;
import shared.GameObserver;

public class GameMenuView implements GameObserver {
    GameMenu gameMenu = new GameMenu();
    GameMenuController gameMenuController;

    @FXML
    ImageView settingsButton;

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    protected void initialize() throws FileNotFoundException {
        this.gameMenuController = gameMenuController.getInstance();
        this.gameMenuController.registerObserver(this);
    }

    public void settingsButtonPressed(MouseEvent event) {
        gameMenuController.settingsPressed();
    }

    public void setSettingsVis(boolean state) {
        settingsButton.setVisible(state);
    }

    @Override
    public void update(boolean state) {
        setSettingsVis(state);
    }
}
