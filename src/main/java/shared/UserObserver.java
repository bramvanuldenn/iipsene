package shared;

import models.User;

public interface UserObserver {
    void update(User user);
}
