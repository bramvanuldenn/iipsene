package shared;

import models.Country;

public interface CountryObserver {
    void update(Country country);
}
