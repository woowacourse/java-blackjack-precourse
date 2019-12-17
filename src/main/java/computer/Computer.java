package computer;

import domain.user.Dealer;
import domain.user.EntryManager;
import domain.user.Player;
import ui.Output;

import java.util.List;

public class Computer {
    private static double BLACKJACK_MUL = 1.50;
    private static int WIN_MUL = 1;
    private static int LOSE_MUL = -1;
    private static int DRAW_MUL = 0;

    private EntryManager players;
    private Dealer dealer;
    private double dealerBettingMoney = 0d;

    public Computer(EntryManager players) {
        this.players = players;
        this.dealer = players.getDealer();
        players.getPlayers().remove(dealer);
    }

    public void calculate() {
        checkBurst();
        checkBlackJackWithDealerBlackJack();
        checkNumberHighScoreThenDealer();
        checkNumberSameScoreWithDealer();
        checkNumberLowerScoreThenDealer();
        displayDealerMoney();
    }

    public void checkBurst() {
        List<Player> burstPlayers = players.burstPlayers();
        if (burstPlayers != null) {
            burstPlayers.forEach(x -> Output.displayResultMoney(
                    x.getName(), x.getBettingMoney() * LOSE_MUL));
            burstPlayers.forEach(x -> dealerBettingMoney
                    -= x.getBettingMoney() * LOSE_MUL);
            players.playerCleanForResult(burstPlayers);
        }
    }

    public void checkBlackJackWithDealerBlackJack() {
        List<Player> blackJackPlayers = players.blackJackPlayers();
        if (dealer.isBlackJack()) {
            blackJackWithDealer(blackJackPlayers);
            return;
        }
        blackJack(blackJackPlayers);
    }

    public void blackJack(List<Player> blackJackPlayers) {
        if (blackJackPlayers != null) {
            blackJackPlayers.forEach(x -> Output.displayResultMoney(
                    x.getName(), x.getBettingMoney() * BLACKJACK_MUL));
            blackJackPlayers.forEach(x -> dealerBettingMoney
                    -= x.getBettingMoney() * BLACKJACK_MUL);
            players.playerCleanForResult(blackJackPlayers);
        }
    }

    public void blackJackWithDealer(List<Player> blackJackPlayers) {
        if (blackJackPlayers != null) {
            blackJackPlayers.forEach(x -> Output.displayResultMoney(
                    x.getName(), x.getBettingMoney() * DRAW_MUL));
            blackJackPlayers.forEach(x -> dealerBettingMoney
                    -= x.getBettingMoney() * DRAW_MUL);
            players.playerCleanForResult(blackJackPlayers);
        }
    }

    public void checkNumberHighScoreThenDealer() {
        List<Player> higherScorePlayers = players.higherScorePlayers(dealer);
        if (higherScorePlayers != null) {
            higherScorePlayers.forEach(x -> Output.displayResultMoney(
                    x.getName(), x.getBettingMoney() * WIN_MUL));
            higherScorePlayers.forEach(x -> dealerBettingMoney
                    -= x.getBettingMoney() * WIN_MUL);
            players.playerCleanForResult(higherScorePlayers);
        }
    }

    public void checkNumberSameScoreWithDealer() {
        List<Player> sameScorePlayers = players.sameScorePlayers(dealer);
        if (sameScorePlayers != null) {
            sameScorePlayers.forEach(x -> Output.displayResultMoney(
                    x.getName(), x.getBettingMoney() * DRAW_MUL));
            sameScorePlayers.forEach(x -> dealerBettingMoney
                    -= x.getBettingMoney() * DRAW_MUL);
            players.playerCleanForResult(sameScorePlayers);
        }
    }

    public void checkNumberLowerScoreThenDealer() {
        if (players.getPlayers() != null) {
            players.getPlayers().forEach(x -> Output.displayResultMoney(
                    x.getName(), x.getBettingMoney() * LOSE_MUL));
            players.getPlayers().forEach(x -> dealerBettingMoney
                    -= x.getBettingMoney() * LOSE_MUL);
        }
    }

    public void displayDealerMoney() {
        Output.displayResultMoney(dealer.getName(), dealerBettingMoney);
    }
}
