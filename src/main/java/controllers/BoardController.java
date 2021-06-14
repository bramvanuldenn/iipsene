package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class BoardController {

        @FXML
        private void startGamePressed(ActionEvent event) {
            event.consume();
            System.out.println("Pressed Button!");
        }
}
