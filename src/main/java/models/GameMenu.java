package models;

import shared.GameObservable;
import shared.GameObserver;

import java.util.ArrayList;
import java.util.List;

public class GameMenu implements GameObservable {

    private boolean settingsVisible = true;
    private List<GameObserver> observers = new ArrayList<GameObserver>();

    public GameMenu() {
    }

    public boolean isSettingsVisible() {
        return settingsVisible;
    }

    public void setSettingsVisibility() {
        this.settingsVisible = !this.settingsVisible;
    }

    public void hideSettings() {
        setSettingsVisibility();
        notifyAllObservers();
    }

    public void register(GameObserver observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for (GameObserver s : observers) {
            s.update(this);
        }
    }

    public void setSettingsVis() {
        setSettingsVisibility();
        notifyAllObservers();
    }
}
