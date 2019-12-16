package domain.user;

import domain.card.Card;

import java.util.*;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer {
    private final List<Card> cards = new ArrayList<>();

    public Dealer() {}

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> drawCards(List<Card> cards) {
        for(int i = 0; i < cards.size(); i++) {
            Collections.shuffle(cards);
            addCard(cards.get(0));
            cards.remove(0);
        }
        return cards;
    }
    // TODO 추가 기능 구현
}
