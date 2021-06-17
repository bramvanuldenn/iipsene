package views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainMenuClosed extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("mainmenu.fxml"));
        stage.setTitle("Very Basic Board Game");
        Label closed = new Label("The room is closed");
        StackPane layout = new StackPane();
        layout.getChildren().addAll(closed, layout, root);
        stage.setScene(new Scene(layout, 600, 700));
        stage.show();
    }
}
