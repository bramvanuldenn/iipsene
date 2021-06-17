package controllers;

import models.GameMenu;
import shared.GameObserver;

public class GameMenuController {
    static GameMenuController gameMenuController;
    GameMenu gameMenu;

    private GameMenuController() {
        gameMenu = new GameMenu();
    }

    public static GameMenuController getInstance(){
        if (gameMenuController == null){
            gameMenuController = new GameMenuController();
        }
        return gameMenuController;
    }

    public void registerObserver(GameObserver abc) {
        gameMenu.register(abc);
    }

    public void settingsPressed() {
        gameMenu.setSettingsVis(gameMenu.isSettingsVisible());
    }
}