package domain.card;



import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {

    @DisplayName("점수 가져오기")
    @Test
    void getScore() {
        Card card = new Card(Symbol.FIVE, Type.DIAMOND);
        assertThat(card.getScore()).isEqualTo(3);
    }

    @DisplayName("에이스 카드인지 확인하기")
    @ParameterizedTest
    @CsvSource(value = {"TEN, false", "ACE, true"})
    void isSymbolAce(Symbol symbol, boolean result){
        Card card = new Card(symbol, Type.DIAMOND);
        assertThat(card.isSymbolAce()).isEqualTo(result);
    }
}
