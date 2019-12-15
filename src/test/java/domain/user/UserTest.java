package domain.user;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void checkAce() {
        User user = new User();
        user.addRandomCard();
        user.addRandomCard();
    }

    @Test
    void isPlayer() {
        Player player = new Player("pobi", 10000);
        assertEquals(true, player.isPlayer());
    }
}