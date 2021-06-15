package views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import controllers.BoardController;

public class GameMenuView {

    BoardController boardController;

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    protected void initialize() throws FileNotFoundException {
        this.boardController = BoardController.getInstance();
    }


    public void settingsButtonPressed(MouseEvent event) {
        System.out.println("button pressed");
    }
}
