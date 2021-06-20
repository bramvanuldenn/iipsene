package controllers;

import models.Dice;
import models.MainMenu;
import shared.MenuObserver;

public class MainMenuController {
    static MainMenuController mainMenuController;
    MainMenu menu;

    private MainMenuController() {
        menu = new MainMenu();
    }

    public static MainMenuController getInstance(){
        if (mainMenuController == null){
            mainMenuController = new MainMenuController();
        }
        return mainMenuController;
    }

    public void setGameMenu() {
        menu.setGameMenu();
    }

    public void registerObserver(MenuObserver abc) {
        menu.register(abc);
    }

}
