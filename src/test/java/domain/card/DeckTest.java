package domain.card;

import domain.user.Player;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DeckTest {
    @Test
    public void pickCardTest() {
        Deck deck = new Deck();

        assertThat(deck.getDeck().size()).isEqualTo(52);
        deck.removeCardFromDeck(deck.getDeck().get(10));
        assertThat(deck.getDeck().size()).isEqualTo(51);
    }
}