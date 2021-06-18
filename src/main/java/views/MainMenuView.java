package views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import controllers.MainMenuController;
import services.FirebaseService;
import shared.MenuObservable;
import shared.MenuObserver;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class MainMenuView implements MenuObserver {
    MainMenuController mainMenuController;

    @FXML
    TextField enteredName = new TextField();

    @FXML
    protected void initialize(){
        this.mainMenuController = MainMenuController.getInstance();
        this.mainMenuController.registerObserver(this);
    }

    public void setGameMenu() {
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("gamemenu.fxml")));
            Stage stage = (Stage) enteredName.getScene().getWindow();
            Scene scene = new Scene(root, 930, 580);
            stage.setScene(scene);
            stage.show();
            System.out.println("set game menu");
        } catch (IOException ex) {
            System.err.println("Error reading file: " + ex);
        }
    }

    public void startGamePressed() throws IOException, ExecutionException, InterruptedException {
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
