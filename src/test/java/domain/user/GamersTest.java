package domain.user;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static domain.card.Symbol.ACE;
import static domain.card.Symbol.KING;
import static org.assertj.core.api.Assertions.assertThat;

class GamersTest {

    private static final Stream<Arguments> sourceHasNotBlackJack() {
        return Stream.of(
                Arguments.of(Arrays.asList(ACE, KING), false),
                Arguments.of(Arrays.asList(ACE, ACE), true),
                Arguments.of(Arrays.asList(ACE, KING, ACE), true)
        );
    }

    @DisplayName("게임 참여자 중에 블랙잭이 존재하는지")
    @ParameterizedTest
    @MethodSource("sourceHasNotBlackJack")
    void hasNotBlackJack(List<Symbol> symbols, boolean result) {
        Dealer dealer = new Dealer();
        for (Symbol symbol : symbols) {
            dealer.addCard(new Card(symbol, Type.DIAMOND));
        }
        Gamers gamers = new Gamers(dealer, new ArrayList<>());

        assertThat(gamers.hasNotBlackJack()).isEqualTo(result);
    }

}