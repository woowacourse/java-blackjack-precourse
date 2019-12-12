package domain.user;

import domain.card.CardFactory;
import domain.card.Deck;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {
    @Test
    public void playerInitTest() {
        Player player = new Player("이름1", 10000d);
        assertThat(player).isNotNull();
    }

    @Test
    public void playerFieldTest() {
        Player player = new Player("이름1", 10000d);
        assertThat(player.getCards()).isNotNull();
    }

    @Test
    public void playerAddCardFromDeckTest() {
        Player player = new Player("이름1", 10000d);
        Deck deck = new Deck();
        player.addCard(deck.drawCard());
        assertThat(player.getCards()).hasSize(1);
    }
}