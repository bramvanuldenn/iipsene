package views;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import controllers.MainMenuController;
import shared.MenuObservable;
import shared.MenuObserver;

public class MainMenuView implements MenuObserver {
    MainMenuController mainMenuController;

    @FXML
    protected void initialize(){
        this.mainMenuController = MainMenuController.getInstance();
        this.mainMenuController.registerObserver(this);
    }

    public void setGameMenu() {
        this.stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        this.scene = new Scene(root, 1000, 800);
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void update(MenuObservable menu) {
        setGameMenu();
    }
}
