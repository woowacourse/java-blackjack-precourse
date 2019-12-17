package game;

import domain.card.CardTest;
import domain.user.Dealer;
import domain.user.DealerTest;
import domain.user.Player;
import domain.user.PlayerTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProfitDeciderTest {

    private Dealer dealer = DealerTest.getDealerFixture();
    private Player player = PlayerTest.getPlayerFixture();
    private List<Player> players = PlayerTest.getPlayersFixture();

    @Test
    void calculatePlayerProfit_PlayerBursted() {
        player.addCard(CardTest.getKingCardFixture());
        player.addCard(CardTest.getKingCardFixture());
        player.addCard(CardTest.getKingCardFixture());

        assertEquals(ProfitDecider.calculatePlayerProfit(dealer, player), player.getBettingMoney() * -1d);
    }

    @Test
    void calculateProfit_PlayerBlackJack() {
        double profit = 0;

        for (Player player : players) {
            player.addCard(CardTest.getAceCardFixture());
            player.addCard(CardTest.getKingCardFixture());
            profit += player.getBettingMoney() * -1.5d;
        }

        assertEquals(ProfitDecider.calculateDealerProfit(dealer, players), profit);

        for (Player player : players) {
            assertEquals(ProfitDecider.calculatePlayerProfit(dealer, player), player.getBettingMoney() * 1.5d);
        }
    }

    @Test
    void calculateProfit_PlayerBlackJack_DealerBlackJack() {
        dealer.addCard(CardTest.getAceCardFixture());
        dealer.addCard(CardTest.getKingCardFixture());

        for (Player player : players) {
            player.addCard(CardTest.getKingCardFixture());
            player.addCard(CardTest.getAceCardFixture());
        }

        assertEquals(ProfitDecider.calculateDealerProfit(dealer, players), 0);
        for (Player player : players) {
            assertEquals(ProfitDecider.calculatePlayerProfit(dealer, player), 0);
        }

    }
}