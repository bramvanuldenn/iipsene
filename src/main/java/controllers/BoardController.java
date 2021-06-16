package controllers;

import services.FirebaseService;
import shared.GameObservable;
import views.GameObserver;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class BoardController {
    static BoardController boardController;

    public static void main(String[] args) {

    }
    public static BoardController getInstance(){
        if (boardController == null){
            boardController = new BoardController();
        }
        GameObservable gameObservable = new GameObservable();
        GameObserver gameObserver = new GameObserver();
        gameObservable.addObserver(gameObserver);
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
