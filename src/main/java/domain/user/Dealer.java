package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends User {
    public Dealer() {}

    public Card openFirstCardDealerMustOpen() {
        List<Card> allCards = openAllCards();

        if (allCards.size() < 1) {
            return null;
        }

        return allCards.get(0);
    }
}
