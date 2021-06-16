package models;

import shared.MenuObservable;
import shared.MenuObserver;

import java.util.ArrayList;
import java.util.List;

public class MainMenu implements MenuObservable {
    private List<MenuObserver> observers = new ArrayList<MenuObserver>();

    public MainMenu() {
    }

    public void setGameMenu() {
        notifyAllObservers();
    }

    public void register(MenuObserver observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for (MenuObserver s : observers) {
            s.update(this);
        }
    }
}
