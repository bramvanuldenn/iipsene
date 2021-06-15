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
import java.util.concurrent.ExecutionException;

import controllers.BoardController;
public class StartMenuView {

    private Stage stage;
    private Scene scene;
    private Parent root;
    BoardController boardController;

    @FXML
    protected void initialize(){
        this.boardController = BoardController.getInstance();
    }

    @FXML
    TextField enteredName = new TextField();

    public void startGamePressed(MouseEvent event) throws IOException, ExecutionException, InterruptedException {
        System.out.println("Button Pressed!");
        String userName = enteredName.getText().toString();
        if (userName != null && !userName.isEmpty()) {
            System.out.println("Valid username!");
            if (BoardController.addPlayer(userName)) {
                FXMLLoader loader = new FXMLLoader();
                FileInputStream fileInputStream = new FileInputStream(("src/assists/views/gamemenu.fxml"));
                this.root = loader.load(fileInputStream);
                this.stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                this.scene = new Scene(root, 1000, 800);
                stage.setScene(scene);
                stage.show();
            }
        } else {
            enteredName.setText("Please Enter a valid username!");
        }
    }
}
