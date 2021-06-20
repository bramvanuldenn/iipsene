package controllers;

import javafx.scene.input.MouseEvent;

import models.User;
import models.Country;
import models.Dice;
import models.GameMenu;

import services.FirebaseService;

import shared.CountryObserver;
import shared.GameObserver;
import shared.DiceObserver;
import shared.UserObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameMenuController {
    static GameMenuController gameMenuController;
    GameMenu gameMenu;
    Country country;
    User user;
    Dice dice;
    private static Country selectedCountry;
    private static User[] Users;
    private static int turn = 0;


    public GameMenuController() {
        gameMenu = new GameMenu();
        country = new Country();
        dice = new Dice();
        user = new User();
        gameMenu.createCountries();
//        countries.get(0).setColor(Color.RED);
        try {
            FirebaseService.addCountries(gameMenu.getCountries());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static GameMenuController getInstance() {
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

    public void registerDiceObserver(DiceObserver diceObserver) {
        this.dice.registerObserver(diceObserver);
    }

    public void registerUserObserver(UserObserver userObserver) { this.user.registerObserver(userObserver);}

    public void settingsPressed() {
        gameMenu.setSettingsVis();
    }

    public void cardsPressed() {
        gameMenu.setCardsVis();
    }

    public void playerPressed() {
        gameMenu.setPlayerVis();
    }

    public List<Country> getGameMenuController() {
        return gameMenu.getCountries();
    }

    public void canvasClicked(MouseEvent e) {
        System.out.println("Clicked in the canvas at x: " + e.getX() + " and y: " + e.getY());
        Country clickedCountry = getCountry(e.getX(), e.getY());
        if (clickedCountry != null) {
            for (Country c : gameMenu.getCountries()) {
                c.setCountrySelected(false);
            }
            clickedCountry.setCountrySelected(true);
            updateCanvas();
            drawCountry(clickedCountry);
            System.out.println("Clicked country " + clickedCountry.getCountryName());
            moveTroops(e);
        }
    }
    public void selectUserCountry(){
        /* selects a country and stores it given that the current player owns it
         * @param mouse for the mouse click location
         */
        for (Country c : gameMenu.getCountries()) {
            //to be uncommented when turns are a thing
//            if (c.inBounds(mouse) && c.numSoldiers > 1) {
                selectedCountry = c;
//                nextMode();
//            }
        }
    }
    public void selectEnemyCountry(){

    }
    public Country getCountry(double clickX, double clickY){
        for (Country c : gameMenu.getCountries()) {
            if (    clickX >= c.getCountryX() &&
                    clickY >= c.getCountryY() &&
                    clickX <= c.getCountryX() + c.getWidth() &&
                    clickY <= c.getCountryY() + c.getHeight()) {
                return c;
            }
        }
        return null;
    }

    public void rollDice() {
        dice.rolDice();
    }

    public void drawCountry(Country country) {
        this.country.drawCountry(country);
    }

    public void updateCanvas() {
        this.gameMenu.updateCanvas();
    }

    public void moveTroops(MouseEvent e) throws NullPointerException {

        HashMap myCountry = new HashMap();
        Country clickedCountry = getCountry(e.getX(), e.getY());
        if (clickedCountry != null) {

            while (myCountry.size() < 2) {

                if (country.getCountryOwner().toString().equals(user.getPlayerName())) {

                    System.out.println("this your country, bitchyWitch");
                    for (int i = 0; i <= 2; i++) {
                        myCountry.put(clickedCountry, "country_" + i);
                        System.out.println(myCountry);
                    }
                    //Select another country to transport troops.
                } else {
                    System.out.println("this is not your country dumbass");
                }
            } //move troops from country_1 to country_2
        }
    }
}