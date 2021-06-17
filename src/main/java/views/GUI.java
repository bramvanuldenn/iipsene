package views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI extends Application {


    public static void main(String [] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("mainmenu.fxml"));
        primaryStage.setTitle("Very Basic Board Game");
        primaryStage.setScene(new Scene(root, 600, 700));
        primaryStage.show();
    }
}