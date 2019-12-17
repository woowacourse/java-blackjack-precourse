package domain.user;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    void hit() {
        Dealer dealer = new Dealer();
        dealer.addFixCard(new Card(Symbol.ACE, Type.다이아몬드));
        dealer.addFixCard(new Card(Symbol.ACE, Type.다이아몬드));
        dealer.addFixCard(new Card(Symbol.ACE, Type.다이아몬드));
        dealer.addFixCard(new Card(Symbol.ACE, Type.다이아몬드));
        dealer.hit();
        dealer.printFinalOutput();
    }
}