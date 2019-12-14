package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer {
    private final int DEALER_CRITERIA = 16;

    private final List<Card> cards = new ArrayList<>();

    public Dealer() {}

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getScore() {
        int score = getCardsPoint();

        if (cardsContainsAce() && (score+10) <= 21) {
            score += 10;
        }
        return score;
    }

    public boolean cardsContainsAce() {
        for (Card card : cards) {
            if (card.isAce()) {
                return true;
            }
        }
        return false;
    }

    private int getCardsPoint() {
        int point = 0;
        for (Card card : cards) {
            point += card.getSymbolValue();
        }
        return point;
    }

    public boolean isWinner(int maxValue) {
        if (getScore() > 21) {
            return false;
        }
        if (getScore() >= maxValue) {
            return true;
        }
        return false;
    }

}
