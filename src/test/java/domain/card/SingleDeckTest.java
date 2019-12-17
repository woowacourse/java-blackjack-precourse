package domain.card;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SingleDeckTest {

    private SingleDeck singleDeck;

    @BeforeEach
    void init() {
        //given
        singleDeck = new SingleDeck();
    }

    @Test
    void shuffle() {
        //when
        singleDeck.shuffle();

        //then
        assertEquals(52, singleDeck.getAll().size());
    }

    @Test
    void pick() {
        Card cardPicked = singleDeck.pick();

        assertNotNull(cardPicked);
        assertEquals(51, singleDeck.getAll().size());
    }
}