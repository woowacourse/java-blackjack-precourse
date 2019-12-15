package domain.user;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void checkAce() {
        User user = new User();
        user.addRandomCard(new Card(Symbol.EIGHT, Type.다이아몬드));
        user.addRandomCard(new Card(Symbol.ACE, Type.다이아몬드));
        assertEquals(true, user.checkAce());
    }
}