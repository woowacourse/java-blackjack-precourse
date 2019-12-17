package domain.user;

import static org.junit.jupiter.api.Assertions.*;

import annotation.TestDescription;
import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import org.junit.jupiter.api.Test;

class DealerTest {
    @Test
    @TestDescription("딜러의 카드가 16이하인 경우, 한장의 카드를 더 받는지 테스트합니다.")
    public void dealerOneMoreCardTest(){
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Symbol.EIGHT, Type.CLUB));
        dealer.addCard(new Card(Symbol.FIVE, Type.CLUB));
        assertEquals(dealer.sumOfCard(),13);
        assertEquals(dealer.shouldHaveOneMoreCard(),true);
    }
}