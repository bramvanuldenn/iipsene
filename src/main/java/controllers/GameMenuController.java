package controllers;

import javafx.scene.input.MouseEvent;
import models.Country;
import models.GameMenu;
import shared.CountryObserver;
import shared.GameObserver;

public class GameMenuController {
    static GameMenuController gameMenuController;
    GameMenu gameMenu;
    Country country;

    private GameMenuController() {
        gameMenu = new GameMenu();
        country = new Country();
    }

    public static GameMenuController getInstance(){
        if (gameMenuController == null){
            gameMenuController = new GameMenuController();
        }
        return gameMenuController;
    }

    public void registerGameObserver(GameObserver abc) {
        gameMenu.register(abc);
    }
    public void registerCountryObserver(CountryObserver abc) {
        this.country.register(abc);
    }

    public void settingsPressed() {
        gameMenu.setSettingsVis(gameMenu.isSettingsVisible());
    }

    public void canvasClicked(MouseEvent e) {
        System.out.println("Clicked in the canvas at x: " + e.getX() + " and y: " + e.getY());
    }

    public void drawCountry(Country country) {
        this.country.drawCountry(country);
    }
}
