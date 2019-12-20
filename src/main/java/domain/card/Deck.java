package domain.card;

import java.util.Stack;

/**
 * 덱을 의미하는 객체
 */
public class Deck {
    private Stack<Card> cards;

    public Deck(Stack<Card> cards) {
        this.cards = cards;
    }

    public Card draw() {
        return cards.pop();
    }

    public void putRandomLocation(Card card) {
        cards.add((int) (Math.random() * cards.size()), card);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
