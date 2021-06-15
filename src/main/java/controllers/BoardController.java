package controllers;

import services.FirebaseService;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class BoardController {
    static BoardController boardController;

    public static BoardController getInstance(){
        if (boardController == null){
            boardController = new BoardController();
        }
        return boardController;
    }


    public static boolean addPlayer(String playerName) throws ExecutionException, InterruptedException, IOException {
        if (FirebaseService.addPlayer(playerName)) {
            System.out.println("user added from controller!");
            return true;
        }
        return false;
    }
}
