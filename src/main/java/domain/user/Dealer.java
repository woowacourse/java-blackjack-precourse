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

    public boolean hasAce() {
        return this.cards.stream()
                .anyMatch(Card::isAce);
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public String getCardInfo() {
        String result =  "딜러의 카드: ";
        for (Card card : this.cards) {
            result += card.getCardInfo();
            result += " ";
        }
        return result;
    }
}
