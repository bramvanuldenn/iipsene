package models;

import java.util.ArrayList;
import java.util.Scanner;

public class User {

    String playerName;
    Integer playerId;
    ArrayList<String> countryArray;
    Scanner input = new Scanner(System.in);

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

    public ArrayList<String> getCountryArray() {
        return countryArray;
    }

    public void setCountryArray(ArrayList<String> countryArray) {
        this.countryArray = countryArray;
    }

    public Scanner getInput() {
        return input;
    }

    public void setInput(Scanner input) {
        this.input = input;
    }
}
