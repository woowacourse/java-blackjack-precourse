package domain.deck;

import domain.card.Card;
import domain.card.CardFactory;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Deck {

    private Stack<Card> cards = new Stack<>();

    public Deck() {
        initCards();
        shuffle();
    }

    public void initCards() {
        List<Card> cards = CardFactory.create();
        this.cards.addAll(cards);
    }

    private void shuffle() {
        Collections.shuffle(cards);
    }

    public Card draw() {
        return cards.pop();
    }
}
