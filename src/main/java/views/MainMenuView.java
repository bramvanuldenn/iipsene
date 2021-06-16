package views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import controllers.MainMenuController;
import javafx.stage.Window;
import services.FirebaseService;
import shared.MenuObservable;
import shared.MenuObserver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainMenuView implements MenuObserver {
    MainMenuController mainMenuController;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    TextField enteredName = new TextField();

    @FXML
    protected void initialize(){
        this.mainMenuController = MainMenuController.getInstance();
        this.mainMenuController.registerObserver(this);
    }

    public void setGameMenu() {
        try{
            this.root = FXMLLoader.load(getClass().getClassLoader().getResource("gamemenu.fxml"));
            this.stage = (Stage) enteredName.getScene().getWindow();
            this.scene = new Scene(root, 1000, 800);
            this.stage.setScene(this.scene);
            this.stage.show();
            System.out.println("set game menu");
        } catch (IOException ex) {
            System.err.println("Error reading file: " + ex);
        }
    }

    public void startGamePressed(MouseEvent event) throws IOException, ExecutionException, InterruptedException {
        String userName = enteredName.getText().toString();
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
