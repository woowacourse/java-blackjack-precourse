package domain.ui;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import domain.user.Player;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserInterfaceMachineTest {
    @Test
    public void printPlayersCards() {
        List<Player> playerList = new ArrayList<Player>();

        playerList.add(new Player("yelim", 1000));
        playerList.add(new Player("helim", 2000));

        playerList.get(0).addCard(new Card(Symbol.ACE, Type.CLUB));
        playerList.get(0).addCard(new Card(Symbol.KING, Type.SPADE));
        playerList.get(1).addCard(new Card(Symbol.TWO, Type.HEART));

        new UserInterface().printPlayersCards(playerList);
    }
}
