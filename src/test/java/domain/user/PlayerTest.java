package domain.user;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Symbol;
import domain.card.Type;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

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

    @Test
    public void playerGetScoreTest() {
        Player player = new Player("이름1", 10000d);
        Deck deck = new Deck();

        assertThat(player.getScore()).isEqualTo(0);
        player.addCard(deck.drawCard());
        player.addCard(deck.drawCard());
        assertThat(player.getScore()).isGreaterThanOrEqualTo(2);
        assertThat(player.getScore()).isLessThanOrEqualTo(21);
    }

    @Test
    public void isDealerTest() {
        Player player = new Player("이름1", 10000d);
        Dealer dealer = new Dealer();

        assertThat(player.isDealer()).isFalse();
        assertThat(dealer.isDealer()).isTrue();
    }

    @Test
    public void isDealerUnderSixteenTest() {
        Dealer dealer = new Dealer();

        dealer.addCard(new Card(Symbol.TEN, Type.SPADE));
        dealer.addCard(new Card(Symbol.TEN, Type.HEART));
        assertThat(dealer.isDealerUnderSixteen()).isFalse();
    }

    @Test
    public void aceCardCountTest() {
        Player player = new Player("이름1", 10000d);

        assertThat(player.aceCardExist()).isFalse();
        player.addCard(new Card(Symbol.ACE, Type.HEART));
        player.addCard(new Card(Symbol.ACE, Type.SPADE));
        player.addCard(new Card(Symbol.ACE, Type.CLUB));
        assertThat(player.aceCardExist()).isTrue();
    }

    @Test
    public void isBurstTest() {
        Player player = new Player("이름1", 10000d);
        player.addCard(new Card(Symbol.TEN, Type.SPADE));
        player.addCard(new Card(Symbol.ACE, Type.HEART));
        assertThat(player.isNotBurst()).isTrue();

        player.addCard(new Card(Symbol.TEN, Type.DIAMOND));
        assertThat(player.isNotBurst()).isTrue();

        player.addCard(new Card(Symbol.ACE, Type.DIAMOND));
        assertThat(player.isNotBurst()).isFalse();
    }

    @Test
    public void getScoreWithAceCheck() {
        Player player = new Player("이름1", 10000d);
        Player player2 = new Player("이름2", 10000d);
        Player player3 = new Player("이름3", 10000d);

        player.addCard(new Card(Symbol.TEN, Type.SPADE));
        player.addCard(new Card(Symbol.ACE, Type.HEART));
        assertThat(player.getScoreWithAceCheck()).isEqualTo(21);

        player2.addCard(new Card(Symbol.JACK, Type.HEART));
        player2.addCard(new Card(Symbol.QUEEN, Type.HEART));
        player2.addCard(new Card(Symbol.ACE, Type.CLUB));
        assertThat(player2.getScoreWithAceCheck()).isEqualTo(21);

        player3.addCard(new Card(Symbol.ACE, Type.SPADE));
        player3.addCard(new Card(Symbol.TEN, Type.CLUB));
        player3.addCard(new Card(Symbol.ACE, Type.HEART));
        player3.addCard(new Card(Symbol.ACE, Type.DIAMOND));
        assertThat(player3.getScoreWithAceCheck()).isEqualTo(13);
    }

    @Test
    public void isBlackJackTest() {
        Player player = new Player("이름1", 10000d);
        player.addCard(new Card(Symbol.TEN, Type.SPADE));
        player.addCard(new Card(Symbol.ACE, Type.HEART));
        assertThat(player.isBlackJack()).isTrue();

        player.addCard(new Card(Symbol.TEN, Type.HEART));
        assertThat(player.isBlackJack()).isFalse();
    }
}