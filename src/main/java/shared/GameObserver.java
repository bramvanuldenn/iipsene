package shared;

import java.util.Observable;
import java.util.Observer;

public class GameObserver implements Observer {
    @Override
    public void update(Observable gameObservable, Object arg) {
        // Handle the state change here.
    }
}
