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

    public String setPlayerName(String str) {
        str = input.next();
        return str;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer id) {
       this.playerId = playerId;
    }

    public ArrayList<String> getCountryArray() {
        return countryArray;
    }

    public void setCountryArray(ArrayList<String> countryArray) {
        this.countryArray = countryArray;
    }

}
