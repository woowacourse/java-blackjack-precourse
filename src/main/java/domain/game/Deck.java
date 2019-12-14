package domain.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domain.card.Card;
import domain.card.CardFactory;

public class Deck {
    private List<Card> deck;

    public Deck() {
        this.deck = new ArrayList<>(CardFactory.create());
        Collections.shuffle(deck);
    }

    public Card draw() {
        // TODO: 카드가 0개 이하가 될 때 예외처리
        return deck.remove(deck.size() - 1);
    }
}
