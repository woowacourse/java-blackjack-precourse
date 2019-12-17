package domain.user;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Gamer 테스트")
class GamerTest {
    @Test
    @DisplayName("상속에 따라 Dealer와 Player 잘 생성되는지 확인")
    void createGamer() {
        Gamer player = new Player("mj", 1000);
        Gamer dealer = new Dealer();

        assertThat(player).isInstanceOf(Player.class);
        assertThat(player).isInstanceOf(Gamer.class);
        assertThat(dealer).isInstanceOf(Dealer.class);
        assertThat(dealer).isInstanceOf(Gamer.class);
    }

    @Test
    @DisplayName("Dealer와 Player 네임 구별 확인")
    void getGamerName() {
        Gamer player = new Player("mj", 1000);
        Gamer dealer = new Dealer();

        assertThat(player.getName()).isEqualTo("mj");
        assertThat(dealer.getName()).isEqualTo("dealer");
    }

    @Test
    @DisplayName("ACE값에 따라 보정된 점수값으로 나오는지 확인")
    void getScoreOfGamer() {
        Gamer player = new Player("mj", 1000);
        List<Card> cards = makeCardList();
        for (Card card : cards) {
            player.addCard(card);
        }
        assertThat(player.getScoreOfGamer()).isEqualTo(13);
    }

    @Test
    @DisplayName("점수가 옮바르게 나오는지 확인")
    void getScoreOfGamerWithAceNot21() {
        Gamer player = new Player("mj", 1000);
        List<Card> cards = makeCardList2();
        for (Card card : cards) {
            player.addCard(card);
        }
        assertThat(player.getScoreOfGamer()).isEqualTo(22);
    }

    private List<Card> makeCardList() {
        Card cardClub = new Card(Symbol.ACE, Type.CLUB);
        Card cardDia = new Card(Symbol.ACE, Type.DIAMOND);
        Card cardHeart = new Card(Symbol.ACE, Type.HEART);

        return Arrays.asList(cardClub, cardDia, cardHeart);
    }

    private List<Card> makeCardList2() {
        Card cardClub = new Card(Symbol.ACE, Type.CLUB);
        Card cardDia = new Card(Symbol.JACK, Type.DIAMOND);
        Card cardHeart = new Card(Symbol.JACK, Type.HEART);
        Card cardSpade = new Card(Symbol.ACE, Type.SPADE);

        return Arrays.asList(cardClub, cardDia, cardHeart, cardSpade);
    }
}