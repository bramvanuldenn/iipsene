package shared;

import models.User;

public interface UserObservable {
    void update(User user);
    void registerObserver(UserObserver observer);
    void notifyAllObservers();
}
