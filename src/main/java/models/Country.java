package models;

import shared.CountryObservable;
import shared.CountryObserver;
import shared.GameObserver;

import java.util.ArrayList;
import java.util.List;


public class Country implements CountryObservable {
    private List<CountryObserver> observers = new ArrayList<CountryObserver>();
    private String countryName;
    private int countryX;
    private int countryY;
    private int width;
    private int height;

    public Country(String countryName, int countryX, int countryY, int width, int height) {
        this.countryName = countryName;
        this.countryX = countryX;
        this.countryY = countryY;
        this.width = width;
        this.height = height;
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

    @Override
    public void register(CountryObserver observer) {

    }

    @Override
    public void notifyAllObservers() {
        for (CountryObserver s : observers) {
            s.update(this);
        }
    }
}
