package domain.user;

import domain.card.CardFactory;
import domain.card.Deck;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class PlayerTest {
    @Test
    void pickCardTest() {
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

    @Test
    void aceTest() {
        Player player = new Player("tester", 5000);
        player.addCard(CardFactory.create().get(0));
        player.addCard(CardFactory.create().get(1));
        player.addCard(CardFactory.create().get(2));
        player.addCard(CardFactory.create().get(3));

        assertThat(player.containAce()).isEqualTo(4);
    }

    @Test
    void aceScoreTest() {
        Player player = new Player("tester", 5000);

        player.addCard(CardFactory.create().get(0));
        assertThat(player.calculateScoreAce(5)).isEqualTo(16);

        player.addCard(CardFactory.create().get(1));
        assertThat(player.calculateScoreAce(5)).isEqualTo(17);

        player.addCard(CardFactory.create().get(2));
        assertThat(player.calculateScoreAce(12)).isEqualTo(15);
    }
}