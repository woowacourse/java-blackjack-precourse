package domain.user;

import java.util.List;
import java.util.ArrayList;
import domain.card.Card;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends User {
    private final List<Card> cards = new ArrayList<>();

    public Dealer() {
    }

    public String dealerCardsInfo(List<Card> cards) {
        return cards.get(0) + "";
    }

    public List<Card> getCards() {
        return cards;
    }

    @Override
    public void addCard(Card card) {
        cards.add(card);
    }
}