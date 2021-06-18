package models;

import shared.GameObservable;
import shared.GameObserver;

import java.util.ArrayList;
import java.util.List;

public class GameMenu implements GameObservable {

    private boolean settingsVisible = true;
    private boolean cardsVisible = true;
    private boolean playerVisible = true;

    private List<GameObserver> observers = new ArrayList<GameObserver>();

    public GameMenu() {
    }

    public void setSettingsVisibility() {
        this.settingsVisible = !this.settingsVisible;
    }

    public void setCardsVisibility() {
        this.cardsVisible = !this.cardsVisible;
    }

    public void setPlayerVisibility() {
        this.playerVisible = !this.playerVisible;
    }

    public boolean isSettingsVisible() {
        return settingsVisible;
    }

    public boolean isCardsVisible() {
        return cardsVisible;
    }

    public boolean isPlayerVisible() {
        return playerVisible;
    }

    public void setSettingsVis() {
        setSettingsVisibility();
        notifyAllObservers();
    }

    public void setCardsVis() {
        setCardsVisibility();
        notifyAllObservers();
    }

    public void setPlayerVis() {
        setPlayerVisibility();
        notifyAllObservers();
    }

    public void register(GameObserver observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for (GameObserver s : observers) {
            s.update(isSettingsVisible(), isCardsVisible(), isPlayerVisible());
        }
    }
}
