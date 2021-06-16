package views;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

import controllers.MainMenuController;
import shared.GameObserver;

public class GameMenuView extends GameObserver {

    MainMenuController mainMenuController;

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    protected void initialize() throws FileNotFoundException {
        this.mainMenuController = MainMenuController.getInstance();
    }


    public void settingsButtonPressed(MouseEvent event) {
        System.out.println("button pressed");
    }
}
