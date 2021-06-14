package controllers;

import services.FirebaseService;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class BoardController {

    public static void addPlayer(String playerName) throws ExecutionException, InterruptedException, IOException {
        FirebaseService.addPlayer(playerName);
    }
}
