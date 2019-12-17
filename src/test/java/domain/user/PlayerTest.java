package domain.user;

import domain.card.Card;
import domain.card.CardFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {

    public static Player getPlayerFixture() {
        return new Player("jaeju", 10000d);
    }

    public static List<Player> getPlayersFixture() {
        List<Player> players = new ArrayList<>();
        players.add(new Player("jaeju", 10000d));
        players.add(new Player("pobi", 20000d));
        return players;
    }

    @Test
    void addCard_ValidInput_ValidOutput() {
        Player player = getPlayerFixture();
        List<Card> cards = CardFactory.create();

        Card card = cards.get(1);

        player.addCard(card);

        assertEquals(false, player.isBlackJack());
    }
}