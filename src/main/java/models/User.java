package models;

import com.google.cloud.firestore.FieldPath;

import java.util.ArrayList;

public class User {

    String playerName;
    Integer playerId;
    public String getPlayerName() {
        return playerName;
    }

    ArrayList<String> countryArray;

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public ArrayList<String> getCountryArray() {
        return countryArray;
    }

    public void setCountryArray(ArrayList<String> countryArray) {
        this.countryArray = countryArray;
    }
}
