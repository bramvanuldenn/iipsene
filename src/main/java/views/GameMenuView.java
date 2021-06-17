package views;

import controllers.GameMenuController;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import models.Country;
import models.GameMenu;
import shared.CountryObserver;
import shared.GameObserver;

import java.util.ArrayList;

public class GameMenuView implements GameObserver, CountryObserver {
    GameMenuController gameMenuController;

    private ArrayList<Country> countries = new ArrayList<>();

    @FXML
    ImageView settingsButton;
    @FXML
    Canvas gameCanvas;

    private GraphicsContext gc;

    @FXML
    protected void initialize() {
        this.gameMenuController = gameMenuController.getInstance();
        this.gameMenuController.registerGameObserver(this);
        this.gameMenuController.registerCountryObserver(this);
        createCountries();
        gc = gameCanvas.getGraphicsContext2D();
        for (int i = 0; i < countries.size(); i++) {
            gameMenuController.drawCountry(countries.get(i));
        }
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
        countries.add(new Country("Middle East", 550, 180, 110, 70));
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
    }
    public void settingsButtonPressed(MouseEvent event) {
        gameMenuController.settingsPressed();
        System.out.println("pressed settings");
    }

    public void canvasClicked(MouseEvent event) {
        gameMenuController.canvasClicked(event);
    }

    public void setSettingsVis(boolean state) {
        if (state) {
            settingsButton.setOpacity(0.0);
        } else {
            settingsButton.setOpacity(1.0);
        }
    }

    public void drawCountry(Country country) {
            gc.setFill(Color.BLACK);
            gc.fillRect(country.getCountryX()-1, country.getCountryY()-1, country.getWidth()+3, country.getHeight()+3);
            gc.setFill(country.getColor());
            gc.fillRect(country.getCountryX(), country.getCountryY(), country.getWidth(), country.getHeight());
    }

    @Override
    public void update(boolean state) {
        setSettingsVis(state);
    }

    @Override
    public void update(Country country) {
        drawCountry(country);
    }
}
