package domain.user;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("참여하는 Gamer 객체 리스트 생성")
class ActiveGamersTest {

    @DisplayName("입력받은 이름과 베팅 리스트를 매핑하여 참여하는 Gamer객체 생성")
    @Test
    public void createActiveGamers() {
        ActiveGamers activeGamers = makeActiveGamers();
        assertThat(activeGamers.size()).isEqualTo(3);

        Gamer player = activeGamers.getGamers().get(0);

        assertThat(player).isInstanceOf(Player.class);
        assertThat(player.getName()).isEqualTo("p1");
        assertThat(((Player) player).getBettingMoney()).isEqualTo(1000.0);
        assertThat(activeGamers.getDealer()).isNotNull();
    }

    @DisplayName("참여하는 Gamer객체 생성시 들어온 이름과 베팅 가격의 사이즈가 다른 경우 Exception")
    @Test
    public void exceptionTestInCreateActiveGamers() {
        List<String> playerNames = Arrays.asList("p1", "p2");
        List<Double> playerBettings = Collections.singletonList(1000.0);

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    ActiveGamers activeGamers = new ActiveGamers(playerNames, playerBettings);
                }
        );
    }

    @DisplayName("주어진 카드를 해당 게임에 참여하는 게이머에게 나누어준다.")
    @Test
    public void addCardToEveryOne() {
        //given
        ActiveGamers activeGamers = makeActiveGamers();
        List<Card> cards = makeCardList();
        //when
        activeGamers.addCardToEveryOne(cards);
        //then
        assertThat(activeGamers.getGamers().get(0).getCards().get(0)).isEqualTo(cards.get(0));
        assertThat(activeGamers.getGamers().get(1).getCards().get(0)).isEqualTo(cards.get(1));
        assertThat(activeGamers.getDealer().getCards().get(0)).isEqualTo(cards.get(2));
    }

    private ActiveGamers makeActiveGamers() {
        List<String> playerNames = Arrays.asList("p1", "p2");
        List<Double> playerBettings = Arrays.asList(1000.0, 2000.0);
        return new ActiveGamers(playerNames, playerBettings);
    }

    private List<Card> makeCardList() {
        Card cardClub = new Card(Symbol.ACE, Type.CLUB);
        Card cardDia = new Card(Symbol.ACE, Type.DIAMOND);
        Card cardHeart = new Card(Symbol.ACE, Type.HEART);

        return Arrays.asList(cardClub, cardDia, cardHeart);
    }

}