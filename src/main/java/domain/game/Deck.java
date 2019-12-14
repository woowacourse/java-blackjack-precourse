package domain.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domain.card.Card;
import domain.card.CardFactory;

public class Deck {
    private static int MINIMUM_CARD_NUM = 1;
    private List<Card> deck;

    public Deck() {
        this.deck = new ArrayList<>(CardFactory.create());
        Collections.shuffle(deck);
    }

    public Card draw() {
        if (deck.size() > MINIMUM_CARD_NUM) {
            return deck.remove(deck.size() - 1);
        }
        deck = new ArrayList<>(CardFactory.create());
        return draw();
    }
}
