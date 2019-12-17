package domain.user;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    @Test
    void isPlayer() {
        Player player = new Player("pobi", 10000);
        assertEquals(true, player.isPlayer());
    }

    @Test
    void calcurateScore() {
        Dealer dealer = new Dealer();
        dealer.addFixCard(new Card(Symbol.ACE, Type.다이아몬드));
        dealer.addFixCard(new Card(Symbol.ACE, Type.다이아몬드));
        dealer.addFixCard(new Card(Symbol.ACE, Type.다이아몬드));
        dealer.addFixCard(new Card(Symbol.ACE, Type.다이아몬드));
        assertEquals(14, dealer.calcurateScore());
    }
}