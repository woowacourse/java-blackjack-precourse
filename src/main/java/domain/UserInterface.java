package domain;

import domain.user.Will;

public interface UserInterface {
    String[] extractNames();
    double getBettingMoney(String name);
    String getWillForMoreCard();
}
