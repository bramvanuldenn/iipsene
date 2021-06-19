package models;

import javafx.scene.paint.Color;

import shared.CountryObservable;
import shared.CountryObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class Country implements CountryObservable, Comparable<Country> {
    private List<CountryObserver> observers = new ArrayList<>();

    private Country country;
    private String countryName;
    private User countryOwner;
    private int countryTroops;
    private int countryX;
    private int countryY;

    public int getCountryZ() {
        return countryZ;
    }

    public void setCountryZ(int countryZ) {
        this.countryZ = countryZ;
    }

    private int countryZ;
    private int width;
    private int height;
    private Color color = Color.GRAY;
    public Set<Country> adjacentCountries;

    public Country(String countryName, int countryX, int countryY, int countryZ, int width, int height) {
        this.countryName = countryName;
        this.countryX = countryX;
        this.countryY = countryY;
        this.countryZ = countryZ;
        this.width = width;
        this.height = height;
    }

    public Country() {

    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getCountryX() {
        return countryX;
    }

    public void setCountryX(int countryX) {
        this.countryX = countryX;
    }

    public int getCountryY() {
        return countryY;
    }

    public void setCountryY(int countryY) {
        this.countryY = countryY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public User getCountryOwner() {
        return countryOwner;
    }

    public void setCountryOwner(User countryOwner) {
        this.countryOwner = countryOwner;
    }

    public int getCountryTroops() {
        return countryTroops;
    }

    public void setCountryTroops(int countryTroops) {
        this.countryTroops = countryTroops;
    }

    public void drawCountry(Country country) {
        this.country = country;
        notifyAllObservers();
    }

    @Override
    public void register(CountryObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (CountryObserver s : observers) {
            s.update(country);
        }
    }

    @Override
    public int compareTo(Country o) {
        return countryName.compareTo(o.countryName);
    }
}
