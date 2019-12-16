package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer {
    private final List<Card> cards = new ArrayList<>();

    public Dealer() {}

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public int getSumScore() {
        int count = 0;
        for (Card card: this.cards) {
            count += card.getSymbolScore();
        }
        return count;
    }

    public boolean getBooleanSumScore() {
        int count = 0;
        for (Card card: this.cards) {
            count += card.getSymbolScore();
        }
        return count < 21;
    }
}
