package domain.card;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DeckTest {
    @Test
    public void drawCardTest() {
        Deck deck = new Deck();
        Card drawnCard = deck.drawCard();
        assertThat(deck.getDeck()).doesNotContain(drawnCard);
    }
}