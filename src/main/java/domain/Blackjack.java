package domain;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.PlayerFactory;
import ui.UserInterface;
import util.BlackjackPrinter;

import java.util.List;

public class Blackjack {

    private UserInterface userInterface;
    private PlayerFactory playerFactory;
    private Dealer dealer;
    private BlackjackPrinter blackjackPrinter;

    public void play() {
        String[] names = userInterface.extractNames();
        List<Player> players = playerFactory.create(names);
        for (Player player : players) {
            player.bet();
        }

        for (int i = 0; i < 2; i++ ) {
            dealer.giveCard(dealer);
            for (Player player : players) {
                dealer.giveCard(player);
            }
        }

        for (Player player : players) {
            player.confirmCards();
        }
        dealer.addAdditionalCard();
        blackjackPrinter.printUser(dealer);
        for (Player player : players) {
            blackjackPrinter.printUser(player);
        }

        blackjackPrinter.printResult(dealer, players);
    }
}
