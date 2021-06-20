package views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;


public class GUI extends Application {


    public static void main(String [] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("mainmenu.fxml")));
        primaryStage.setTitle("Very Basic Board Game");
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.show();
    }
}
