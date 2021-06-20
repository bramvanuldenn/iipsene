package controllers;

import models.Country;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GameMenuControllerTest {

    GameMenuController controller = new GameMenuController();

    @Test
    public void clickOnCountry() {
        Country country = controller.getCountries().get(0);
        //klikken er beetje naast, geen enkel land is kleiner dan 10 :)
        double clickX = country.getCountryX() + 5;
        double clickY = country.getCountryY() + 5;
        //land selecteren met deze x en y waarden
        Country clickedCountry = controller.getCountry(clickX, clickY);
        assertEquals(country.getCountryName(), clickedCountry.getCountryName());
    }

    @Test
    public void clickOnNoCountry() {
        double clickX = 0.0;
        double clickY = 0.0;
        //land selecteren met deze x en y waarden, hier moet niets liggen!
        Country clickedCountry = controller.getCountry(clickX, clickY);
        assertNull(clickedCountry);
    }

}
