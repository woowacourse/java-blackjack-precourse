package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends User {
    private final List<Card> cards = new ArrayList<>();

    public Dealer() {
    }

    public void updateBalanceAfterWin (int amount) {
        balance -= amount;
    }

}
