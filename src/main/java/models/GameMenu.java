package models;


import shared.GameObservable;
import shared.GameObserver;

import java.util.ArrayList;
import java.util.List;

public class GameMenu implements GameObservable {

    private boolean settingsVisible = true;
    private List<GameObserver> observers = new ArrayList<>();

    public GameMenu() {
    }

    public boolean isSettingsVisible() {
        return settingsVisible;
    }

    public void setSettingsVisibility() {
        this.settingsVisible = !this.settingsVisible;
    }

    public void register(GameObserver observer) {
        observers.add(observer);
    }

    public void setSettingsVis(boolean settingsVisible) {
        setSettingsVisibility();
        notifyAllObservers();
    }

    public void notifyAllObservers() {
        for (GameObserver s : observers) {
            s.update(this.settingsVisible);
        }
    }
}
