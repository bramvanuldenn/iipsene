package models;

import java.util.ArrayList;

/* Alle eigenschappen die een speler kan nodig hebben.
 * vanaf hier kunnen wij alle info van alle spelers controlleren door de setters and getters.
 * @author Hayyan Mezher.
 */

public class User {

    private String playerName;
    private Integer playerId;
    private ArrayList<Double> countryArray;
    private ArrayList<Double> cardArray;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public ArrayList<Double> getCountryArray() {
        return countryArray;
    }

    public void setCountryArray(ArrayList<Double> countryArray) {
        this.countryArray = countryArray;
    }

    public ArrayList<Double> getCardArray() {
        return cardArray;
    }

    public void setCardArray(ArrayList<Double> cardArray) {
        this.cardArray = cardArray;
    }
}
