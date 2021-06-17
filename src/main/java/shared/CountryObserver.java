package shared;

import models.Country;

public interface CountryObserver {
    public void update(Country country);
}
