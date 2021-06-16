package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import services.FirebaseService;
import models.MainMenu;
import shared.MenuObservable;
import shared.MenuObserver;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class MainMenuController {
    static MainMenuController mainMenuController;
    MainMenu menu;

    @FXML
    TextField enteredName = new TextField();

    private MainMenuController() {
        menu = new MainMenu();
    }

    public static MainMenuController getInstance(){
        if (mainMenuController == null){
            mainMenuController = new MainMenuController();
        }
        return mainMenuController;
    }

    public void registerObserver(MenuObserver menu) {
        MenuObservable.register(menu);
    }



    public void startGamePressed(MouseEvent event) throws IOException, ExecutionException, InterruptedException {
        String userName = enteredName.getText().toString();
        if (userName != null && !userName.isEmpty()) {
            if (FirebaseService.addPlayer(userName)) {
            }
        } else {
            enteredName.setText("Please Enter a valid username!");
        }
    }
}
