package models;

import java.util.ArrayList;

public class User {

    String playerName;
    Integer playerId;
    ArrayList<Double> countryArray;
    ArrayList<Double> cardArray;

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
