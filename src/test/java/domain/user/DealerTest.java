package domain.user;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import domain.dispenser.RandomDispenser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @DisplayName("딜러가 더 카드를 받을 수 있는지 확인")
    @ParameterizedTest
    @CsvSource(value = {"ACE,FIVE,true", "ACE,SIX,false"})
    void canReceive(Symbol first, Symbol second, boolean result) {
        Dealer dealer = new Dealer(new RandomDispenser());
        Card firstCard = new Card(first, Type.DIAMOND);
        Card secondCard = new Card(second, Type.DIAMOND);
        dealer.addCard(firstCard);
        dealer.addCard(secondCard);

        assertThat(dealer.canReceive()).isEqualTo(result);
    }

}