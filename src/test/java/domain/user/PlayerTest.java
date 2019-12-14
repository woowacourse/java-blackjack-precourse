package domain.user;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    private static Stream<Arguments> sourceGetResultMoney() {
        return Stream.of(
                Arguments.of(aPlayer("1", 100, Symbol.ACE, Symbol.KING), true, false, 0),   // 딜러와 플레이어가 블랙잭
                Arguments.of(aPlayer("1", 100, Symbol.ACE, Symbol.ACE), true, false, -100), // 딜러만 블랙잭
                Arguments.of(aPlayer("1", 100, Symbol.ACE, Symbol.KING), false, true, 150), // 플레이어만 블랙잭
                Arguments.of(aPlayer("1", 100, Symbol.ACE, Symbol.TWO), false, true, 100),  // 플레이어 일반 승리
                Arguments.of(aPlayer("1", 100, Symbol.ACE, Symbol.TWO), false, false, -100) // 플레이어 일반 패배
        );
    }

    private static Player aPlayer(String name, double money, Symbol... symbols) {
        Player player = new Player(name, money);
        for (Symbol symbol : symbols) {
            player.addCard(new Card(symbol, Type.DIAMOND));
        }
        return player;
    }

    @DisplayName("결과에 따른 베팅금액 받기")
    @ParameterizedTest
    @MethodSource("sourceGetResultMoney")
    void getResultMoney(Player player, boolean isDealerBlackJack, boolean isWinner, double result) {
        assertThat(player.getResultMoney(isDealerBlackJack, isWinner)).isEqualTo(result);
    }
}