package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class CardTest {

    @DisplayName("카드 점수 가져오기")
    @Test
    void getScore() {
        Card card = new Card(Symbol.EIGHT, Type.DIAMOND);

        assertThat(card.getScore()).isEqualTo(8);
    }

    @DisplayName("ACE 카드인지 확인하기")
    @ParameterizedTest
    @CsvSource(value = {"ACE,true", "EIGHT,false"})
    void isAce(Symbol symbol, boolean result) {
        Card card = new Card(symbol, Type.DIAMOND);

        assertThat(card.isAce()).isEqualTo(result);
    }
}