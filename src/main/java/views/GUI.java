package views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class GUI extends Application {
    Scene scene1, scene2;
    private Button button1, button2;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        FileInputStream fileInputStream = new FileInputStream(("../iipsene-master/src/assists/views/mainmenu.fxml"));
        Parent root = loader.load(fileInputStream);
        //the entire window we work with
        window.setTitle("i fu");

        button2 = new Button("Stair way to heaven");
        button1 = new Button("Ik rijd naar jou");
        Label label = new Label("drunk");

        // 3 using lambda expressions the code can be much shorter and easier to read.
        button2.setOnAction(e -> window.setScene(scene1));

       //positioning stuff
        VBox layout2 = new VBox();
        layout2.getChildren().addAll(button2, label);
        VBox layout1 = new VBox();
        layout1.getChildren().addAll(button1);

        //define the contents of the window using the layout and sizes.
        scene2 = new Scene(root, 350, 300);
        scene1 = new Scene(layout1, 350, 300);
        window.setScene(scene2);
        layout1.setAlignment(Pos.CENTER);
        window.show();
    }
}
