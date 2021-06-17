package views;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import controllers.MainMenuController;
import models.MainMenu;
import services.FirebaseService;
import shared.MenuObservable;
import shared.MenuObserver;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class MainMenuView implements MenuObserver {
    MainMenuController mainMenuController;

    @FXML
    TextField enteredName = new TextField();
    @FXML
    Label closed = new Label();

    @FXML
    protected void initialize(){
        this.mainMenuController = MainMenuController.getInstance();
        this.mainMenuController.registerObserver(this);
    }

    public void setGameMenu() {
        /*
        this.stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        this.scene = new Scene(root, 1000, 800);
        stage.setScene(scene);
        stage.show();
         */
        System.out.println("set game menu");
    }

    public void startGamePressed() throws IOException, ExecutionException, InterruptedException, NullPointerException {
        String userName = enteredName.getText();
        if (userName != null && !userName.isEmpty()) {
            if (FirebaseService.addPlayer(userName)) {
                mainMenuController.setGameMenu();
            }
        } else {
            enteredName.setText("Please Enter a valid username!");
        }
    }

    @Override
    public void update(MenuObservable menu) {
        setGameMenu();
    }
}
