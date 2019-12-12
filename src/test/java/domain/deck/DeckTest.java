package domain.deck;

import domain.card.Card;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    private Deck deck = new Deck();

    @Test
    void drawCard() {
        deck.initCards();
        deck.shuffle();
        assertNotNull(deck.draw());
    }
}