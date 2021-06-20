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
import java.util.List;
import java.util.TreeSet;

public class GameMenuController {
    List<Country> countries;
    static GameMenuController gameMenuController;
    GameMenu gameMenu;
    Country country;
    User user;
    Dice dice;
    private static Country selectedCountry;
    private static User[] Users;
    private static int turn = 0;


    public GameMenuController() throws Exception {
        gameMenu = new GameMenu();
        country = new Country();
        dice = new Dice();
        user = new User();
        // Only activate when we need countries in the database
//        createCountriesInDatabase();

        List<User> players = FirebaseService.fetchPlayers();
        this.countries = FirebaseService.fetchCountries(players);
        System.out.println(this.countries.size());
    }

    private void createCountriesInDatabase() {
        this.countries = new ArrayList<>();
        createCountries();
        // countries.get(0).setColor(Color.RED);
        try {
            FirebaseService.addCountries(countries);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static GameMenuController getInstance() throws Exception {
        if (gameMenuController == null){
            gameMenuController = new GameMenuController();
        }
        return gameMenuController;
    }

    public List<Country> getCountries() {
        return this.countries;
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

    public void canvasClicked(MouseEvent e) {
        System.out.println("Clicked in the canvas at x: " + e.getX() + " and y: " + e.getY());
        Country clickedCountry = getCountry(e.getX(), e.getY());
        if (clickedCountry != null) {
            for (Country c : countries) {
                c.setCountrySelected(false);
            }
            clickedCountry.setCountrySelected(true);
            updateCanvas();
            drawCountry(clickedCountry);
            System.out.println("Clicked country " + clickedCountry.getCountryName());
        }
    }
    public void selectUserCountry(){
        /* selects a country and stores it given that the current player owns it
         * @param mouse for the mouse click location
         */
        for (Country c : countries) {
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
        for (Country c : countries) {
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

    public void createCountries() {
        countries.add(new Country("Alaska", 60, 30, 80, 60));
        countries.add(new Country("Alberta", 140, 80, 80, 50));
        countries.add(new Country("Central America", 170, 210, 65, 60));
        countries.add(new Country("Eastern United States", 230, 130, 50, 80));
        countries.add(new Country("Greenland", 310, 20, 80, 60));
        countries.add(new Country("Northwest Territory", 140, 30, 120, 50));
        countries.add(new Country("Ontario", 220, 80, 60, 50));
        countries.add(new Country("Quebec", 280, 90, 50, 70));
        countries.add(new Country("Western United States", 140, 130, 90, 80));
        countries.add(new Country("Venezuela", 230, 270, 70, 50));
        countries.add(new Country("Brazil", 260, 320, 90, 120));
        countries.add(new Country("Peru", 220, 320, 40, 120));
        countries.add(new Country("Argentina", 230, 440, 70, 100));
        countries.add(new Country("Great Britain", 430, 110, 20, 40));
        countries.add(new Country("Iceland", 400, 90, 20, 20));
        countries.add(new Country("Northern Europe", 470, 140, 60, 40));
        countries.add(new Country("Scandinavia", 480, 20, 50, 100));
        countries.add(new Country("Ukraine", 530, 70, 100, 110));
        countries.add(new Country("Southern Europe", 470, 180, 80, 40));
        countries.add(new Country("Western Europe", 410, 160, 60, 40));
        countries.add(new Country("Madagascar", 570, 400, 20, 40));
        countries.add(new Country("Egypt", 480, 240, 70, 40));
        countries.add(new Country("North Africa", 390, 240, 90, 90));
        countries.add(new Country("East Africa", 480, 280, 80, 110));
        countries.add(new Country("Congo", 420, 330, 60, 60));
        countries.add(new Country("South Africa", 450, 390, 80, 110));
        countries.add(new Country("Syria", 550, 180, 110, 70));
        countries.add(new Country("Afghanistan", 630, 110, 70, 70));
        countries.add(new Country("Ural", 630, 55, 80, 55));
        countries.add(new Country("India", 660, 180, 60, 100));
        countries.add(new Country("China", 700, 110, 90, 70));
        countries.add(new Country("Siberia", 710, 35, 30, 75));
        countries.add(new Country("Siam", 720, 180, 40, 70));
        countries.add(new Country("Mongolia", 740, 80, 60, 30));
        countries.add(new Country("Irkutsk", 740, 55, 60, 25));
        countries.add(new Country("Yakutsk", 740, 30, 60, 25));
        countries.add(new Country("Kamchatka", 800, 30, 30, 70));
        countries.add(new Country("Japan", 840, 80, 20, 40));
        countries.add(new Country("Indonesia", 760, 300, 30, 30));
        countries.add(new Country("Western Australia", 770, 360, 60, 70));
        countries.add(new Country("Eastern Australia", 830, 360, 50, 70));
        countries.add(new Country("New Guinea", 830, 310, 40, 25));

        countries.get(0).adjacentCountries = new TreeSet<>();
        countries.get(0).adjacentCountries.add(countries.get(1));
        countries.get(0).adjacentCountries.add(countries.get(5));
        countries.get(0).adjacentCountries.add(countries.get(36));

        countries.get(1).adjacentCountries = new TreeSet<>();
        countries.get(1).adjacentCountries.add(countries.get(5));
        countries.get(1).adjacentCountries.add(countries.get(6));
        countries.get(1).adjacentCountries.add(countries.get(8));

        countries.get(2).adjacentCountries = new TreeSet<>();
        countries.get(2).adjacentCountries.add(countries.get(3));
        countries.get(2).adjacentCountries.add(countries.get(8));
        countries.get(2).adjacentCountries.add(countries.get(9));

        countries.get(3).adjacentCountries = new TreeSet<>();
        countries.get(3).adjacentCountries.add(countries.get(6));
        countries.get(3).adjacentCountries.add(countries.get(7));
        countries.get(3).adjacentCountries.add(countries.get(8));

        countries.get(4).adjacentCountries = new TreeSet<>();
        countries.get(4).adjacentCountries.add(countries.get(5));
        countries.get(4).adjacentCountries.add(countries.get(6));
        countries.get(4).adjacentCountries.add(countries.get(7));
        countries.get(4).adjacentCountries.add(countries.get(14));

        countries.get(5).adjacentCountries = new TreeSet<>();
        countries.get(5).adjacentCountries.add(countries.get(6));

        countries.get(6).adjacentCountries = new TreeSet<>();
        countries.get(6).adjacentCountries.add(countries.get(7));
        countries.get(6).adjacentCountries.add(countries.get(8));

        countries.get(7).adjacentCountries = new TreeSet<>();

        countries.get(8).adjacentCountries = new TreeSet<>();

        countries.get(9).adjacentCountries = new TreeSet<>();
        countries.get(9).adjacentCountries.add(countries.get(10));
        countries.get(9).adjacentCountries.add(countries.get(11));

        countries.get(10).adjacentCountries = new TreeSet<>();
        countries.get(10).adjacentCountries.add(countries.get(11));
        countries.get(10).adjacentCountries.add(countries.get(12));
        countries.get(10).adjacentCountries.add(
                countries.get(22));

        countries.get(11).adjacentCountries = new TreeSet<>();
        countries.get(11).adjacentCountries.add(countries.get(12));

        countries.get(12).adjacentCountries = new TreeSet<>();

        countries.get(13).adjacentCountries = new TreeSet<>();
        countries.get(13).adjacentCountries.add(countries.get(14));
        countries.get(13).adjacentCountries.add(countries.get(15));
        countries.get(13).adjacentCountries.add(countries.get(16));
        countries.get(13).adjacentCountries.add(countries.get(19));

        countries.get(14).adjacentCountries = new TreeSet<>();

        countries.get(15).adjacentCountries = new TreeSet<>();
        countries.get(15).adjacentCountries.add(countries.get(16));
        countries.get(15).adjacentCountries.add(countries.get(17));
        countries.get(15).adjacentCountries.add(countries.get(18));
        countries.get(15).adjacentCountries.add(countries.get(19));

        countries.get(16).adjacentCountries = new TreeSet<>();
        countries.get(16).adjacentCountries.add(countries.get(17));

        countries.get(17).adjacentCountries = new TreeSet<>();
        countries.get(17).adjacentCountries.add(countries.get(18));
        countries.get(17).adjacentCountries.add(countries.get(26));
        countries.get(17).adjacentCountries.add(countries.get(27));
        countries.get(17).adjacentCountries.add(countries.get(28));

        countries.get(18).adjacentCountries = new TreeSet<>();
        countries.get(18).adjacentCountries.add(countries.get(19));
        countries.get(18).adjacentCountries.add(countries.get(21));
        countries.get(18).adjacentCountries.add(countries.get(22));
        countries.get(18).adjacentCountries.add(countries.get(26));

        countries.get(19).adjacentCountries = new TreeSet<>();
        countries.get(19).adjacentCountries.add(countries.get(22));

        countries.get(20).adjacentCountries = new TreeSet<>();
        countries.get(20).adjacentCountries.add(countries.get(23));
        countries.get(20).adjacentCountries.add(countries.get(25));

        countries.get(21).adjacentCountries = new TreeSet<>();
        countries.get(21).adjacentCountries.add(countries.get(22));
        countries.get(21).adjacentCountries.add(countries.get(23));
        countries.get(21).adjacentCountries.add(countries.get(26));

        countries.get(22).adjacentCountries = new TreeSet<>();
        countries.get(22).adjacentCountries.add(countries.get(23));
        countries.get(22).adjacentCountries.add(countries.get(24));

        countries.get(23).adjacentCountries = new TreeSet<>();
        countries.get(23).adjacentCountries.add(countries.get(24));
        countries.get(23).adjacentCountries.add(countries.get(25));
        countries.get(23).adjacentCountries.add(countries.get(26));

        countries.get(24).adjacentCountries = new TreeSet<>();
        countries.get(24).adjacentCountries.add(countries.get(25));

        countries.get(25).adjacentCountries = new TreeSet<>();

        countries.get(26).adjacentCountries = new TreeSet<>();
        countries.get(26).adjacentCountries.add(countries.get(27));
        countries.get(26).adjacentCountries.add(countries.get(29));

        countries.get(27).adjacentCountries = new TreeSet<>();
        countries.get(27).adjacentCountries.add(countries.get(28));
        countries.get(27).adjacentCountries.add(countries.get(29));
        countries.get(27).adjacentCountries.add(countries.get(30));

        countries.get(28).adjacentCountries = new TreeSet<>();
        countries.get(28).adjacentCountries.add(countries.get(30));
        countries.get(28).adjacentCountries.add(countries.get(31));

        countries.get(29).adjacentCountries = new TreeSet<>();
        countries.get(29).adjacentCountries.add(countries.get(30));
        countries.get(29).adjacentCountries.add(countries.get(32));

        countries.get(30).adjacentCountries = new TreeSet<>();
        countries.get(30).adjacentCountries.add(countries.get(31));
        countries.get(30).adjacentCountries.add(countries.get(32));
        countries.get(30).adjacentCountries.add(countries.get(33));

        countries.get(31).adjacentCountries = new TreeSet<>();
        countries.get(31).adjacentCountries.add(countries.get(33));
        countries.get(31).adjacentCountries.add(countries.get(34));
        countries.get(31).adjacentCountries.add(countries.get(35));

        countries.get(32).adjacentCountries = new TreeSet<>();
        countries.get(32).adjacentCountries.add(countries.get(38));

        countries.get(33).adjacentCountries = new TreeSet<>();
        countries.get(33).adjacentCountries.add(countries.get(34));
        countries.get(33).adjacentCountries.add(countries.get(36));
        countries.get(33).adjacentCountries.add(countries.get(37));

        countries.get(34).adjacentCountries = new TreeSet<>();
        countries.get(34).adjacentCountries.add(countries.get(35));
        countries.get(34).adjacentCountries.add(countries.get(36));

        countries.get(35).adjacentCountries = new TreeSet<>();
        countries.get(35).adjacentCountries.add(countries.get(36));

        countries.get(36).adjacentCountries = new TreeSet<>();
        countries.get(36).adjacentCountries.add(countries.get(37));

        countries.get(37).adjacentCountries = new TreeSet<>();

        countries.get(38).adjacentCountries = new TreeSet<>();
        countries.get(38).adjacentCountries.add(countries.get(39));
        countries.get(38).adjacentCountries.add(countries.get(41));

        countries.get(39).adjacentCountries = new TreeSet<>();
        countries.get(39).adjacentCountries.add(countries.get(40));
        countries.get(39).adjacentCountries.add(countries.get(41));

        countries.get(40).adjacentCountries = new TreeSet<>();
        countries.get(40).adjacentCountries.add(countries.get(41));

        countries.get(41).adjacentCountries = new TreeSet<>();

    }

    public void moveTroops(Country clickedCountry) {
        /* if (Country country.countryClicked)
         * if (country in User user.countryArray)
         *
         */
    }
}
