package domain.user;

import domain.card.Deck;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class PlayerTest {
    @Test
    public void pickCardTest() {
        Player player = new Player("Bob", 1000);
        Deck deck = new Deck();

        assertThat(player.getCards().size()).isEqualTo(0);
        player.pickCardFromDeck(deck);
        player.pickCardFromDeck(deck);
        player.pickCardFromDeck(deck);
        player.pickCardFromDeck(deck);
        player.pickCardFromDeck(deck);
        player.pickCardFromDeck(deck);
        player.pickCardFromDeck(deck);
        assertThat(player.getCards().size()).isEqualTo(7);
    }
}