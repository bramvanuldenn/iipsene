package models;

import java.util.ArrayList;
import java.util.Scanner;

public class User {

    String playerName;
    ArrayList<Integer> playerId = new ArrayList<>();
    ArrayList<String> countryArray;
    Scanner input = new Scanner(System.in);

    public String getPlayerName() {
        return playerName;
    }

    public String setPlayerName(String str) {
        str = input.next();
        return str;
    }

    public ArrayList<Integer> getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer id) {
       playerId.add(id);
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
