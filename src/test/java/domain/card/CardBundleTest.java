package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class CardBundleTest {

    @DisplayName("카드 추가하기")
    @Test
    void addCard() {
        CardBundle cardBundle = new CardBundle();

        Card card = new Card(Symbol.ACE, Type.DIAMOND);
        cardBundle.addCard(card);

        assertThat(cardBundle.getCardSum()).isEqualTo(11);
    }

    @DisplayName("카드의 합 구하기")
    @ParameterizedTest
    @CsvSource(value = {"ACE,ACE,12", "ACE,KING,21", "ACE,NINE,20"})
    void getCardSum(Symbol firstSymbol, Symbol secondSymbol, int sum) {
        Card firstCard = new Card(firstSymbol, Type.DIAMOND);
        Card secondCard = new Card(secondSymbol, Type.DIAMOND);

        CardBundle cardBundle = new CardBundle();
        cardBundle.addCard(firstCard);
        cardBundle.addCard(secondCard);

        assertThat(cardBundle.getCardSum()).isEqualTo(sum);
    }

    @DisplayName("블랙잭인지 확인하기")
    @ParameterizedTest
    @CsvSource(value = {"ACE,KING,true", "ACE,NINE,false"})
    void isBlackJack(Symbol firstSymbol, Symbol secondSymbol, boolean result) {
        Card firstCard = new Card(firstSymbol, Type.DIAMOND);
        Card secondCard = new Card(secondSymbol, Type.DIAMOND);

        CardBundle cardBundle = new CardBundle();
        cardBundle.addCard(firstCard);
        cardBundle.addCard(secondCard);

        assertThat(cardBundle.isBlackJack()).isEqualTo(result);
    }

    @DisplayName("카드의 합이 21보다 큰지 확인")
    @ParameterizedTest
    @CsvSource(value = {"ACE,NINE,KING,false", "TWO,NINE,KING,false", "KING,KING,KING,true"})
    void isOver(Symbol first, Symbol second, Symbol third, boolean result) {
        Card firstCard = new Card(first, Type.DIAMOND);
        Card secondCard = new Card(second, Type.DIAMOND);
        Card thirdCard = new Card(third, Type.DIAMOND);

        CardBundle cardBundle = new CardBundle();
        cardBundle.addCard(firstCard);
        cardBundle.addCard(secondCard);
        cardBundle.addCard(thirdCard);

        assertThat(cardBundle.isOver()).isEqualTo(result);
    }
}