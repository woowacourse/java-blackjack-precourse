package domain.user;

import common.BlackjackConfig;
import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends User {
    public Dealer() {}

    @Override
    public String toString() {
        return BlackjackConfig.DEALER_NAME;
    }

    public void win(double money) {
        profit += money;
    }
    public void lose(double money) {
        profit -= money;
    }
}
