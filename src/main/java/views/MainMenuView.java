package views;

import com.google.cloud.firestore.DocumentReference;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import controllers.MainMenuController;
import services.FirebaseService;
import services.InitFirebase;
import shared.MenuObservable;
import shared.MenuObserver;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class MainMenuView implements MenuObserver {
    MainMenuController mainMenuController;

    @FXML
    TextField enteredName = new TextField();

    Boolean hasClickedStart = false;
    Boolean canStartGame = false;

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

    public interface firebaseCallback {
        void onCallback(Map value);
    }

    public void startGamePressed() throws Exception {
        if (hasClickedStart) {return;}
        hasClickedStart = true;
        String userName = enteredName.getText();
        if (userName != null && !userName.isEmpty()) {
            FirebaseService.addPlayer(userName);
            FirebaseService.addUsersListener(new firebaseCallback() {
                @Override
                public void onCallback(Map value) {
                    if (value.size() >= 4) {
                        //assign countries to players and save to firebase
                        try {
                            FirebaseService.assignCountries();
                        } catch (Exception err) {
                            System.out.println("Fatal error: " + err.getMessage());
                            err.printStackTrace();
                            return;
                        }
                        //todo implement here
                        //start the game on every1's platform
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                mainMenuController.setGameMenu();
                            }
                        });
                    }
                }
            });
        } else {
            enteredName.setText("Please Enter a valid username!");
        }
    }

    @Override
    public void update(MenuObservable menu) {
        setGameMenu();
    }
}
