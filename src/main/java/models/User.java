package models;

import javafx.scene.paint.Color;
import shared.UserObservable;
import shared.UserObserver;

import java.util.*;

public class User implements UserObservable, UserObserver {

    String playerName;
    Integer playerId;
    ArrayList<Double> countryArray;
    ArrayList<Double> cardArray;
    ArrayList<UserObserver> userObserver = new ArrayList<>();
    private User user;
    private Color color;

    public User(Integer playerId) {
        this.playerId = playerId;
    }

    public User() {}

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

    public ArrayList<UserObserver> getUserObserver() {
        return userObserver;
    }

    public void setUserObserver(ArrayList<UserObserver> userObserver) {
        this.userObserver = userObserver;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void update(User user) {
        userObserver.add(user);
    }

    @Override
    public void registerObserver(UserObserver observer) {
        this.userObserver.add(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (UserObserver eachUser : this.userObserver) {
            user.update(user);
        }
    }
}