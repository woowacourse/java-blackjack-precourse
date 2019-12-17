package domain.user;

import java.util.List;
import java.util.ArrayList;
import domain.card.Card;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends User {
    private final String name = "딜러";
    private final List<Card> cards = new ArrayList<>();

    public Dealer() {
    }

    public void userCardsInfo(List<Card> cards) {
        System.out.print("딜러 : ");
        System.out.println(cards.get(0).toString());
    }

    public List<Card> getCards() {
        return cards;
    }

    @Override
    public void addCard(Card card) {
        cards.add(card);
    }
}
