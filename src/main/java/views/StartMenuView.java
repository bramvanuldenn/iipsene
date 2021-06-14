package views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.IOException;

public class StartMenuView {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    TextField enteredName = new TextField();

    public void startGamePressed(MouseEvent event) throws IOException {
        System.out.println("Button Pressed!");
        String userName = enteredName.getText().toString();
        if (userName != null && !userName.isEmpty()) {
            System.out.println("Valid username!");
        } else {
            enteredName.setText("Please Enter a valid username!");
        }
    }
}
