package domain.user;

import domain.card.Card;

import java.util.List;

/**
 * 딜러와 플레이어를 포괄하는 객체
 */
abstract public class Participant {
    String name;
    List<Card> cards;

    protected Participant(String name) {
        this.name = name;
    }

    abstract public void addCard(Card card);

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public Card getCard(int index) {
        if (index >= cards.size())
            return null;
        return cards.get(index);
    }

}
