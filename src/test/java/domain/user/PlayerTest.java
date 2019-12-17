package domain.user;

import domain.card.Card;
import domain.card.CardFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private static final String name = "jaeju";
    private static final double battingmoney = 1000d;
    Player player;

    @BeforeEach
    void setUp() {
        player = new Player(name, battingmoney);
    }

    @Test
    void addCard_ValidInput_ValidOutput() {
        List<Card> cards = CardFactory.create();

        Card card = cards.get(1);

        player.addCard(card);

        assertEquals(false, player.isBlackJack());
    }
}