package domain;

import domain.user.Will;

public interface UserInterface {
    String[] extractNames();
    int getBettingMoney();
    Will getWillForMoreCard();
}
