package controller;

import domain.user.Dealer;
import domain.user.Gamers;
import domain.user.Player;
import view.Output;

import java.util.List;

public class StakeManager {
    private static final double BLACK_JACK_WIN_MULTIPLE = 1.5D;
    private static final int WIN_MULTIPLE = 1;
    private static final int LOSE_MULTIPLE = -1;
    private static final int DRAW_MULTIPLE = 0;

    private Gamers gamers;
    private Dealer dealer;
    private double dealerMoney = 0d;

    public StakeManager(Gamers gamers) {
        this.gamers = gamers;
        this.dealer = gamers.findDealer();
        gamers.getPlayers().remove(dealer);
    }

    public void start() {
        checkBurst();
        checkBlackJackWithDealerBlackJack();
        checkNumberHighScoreThenDealer();
        checkNumberSameScoreWithDealer();
        checkNumberLowerScoreThenDealer();
        showDealerMoney();
    }

    public void checkBurst() {
        List<Player> burstPlayers = gamers.isBurst();
        if (burstPlayers != null) {
            burstPlayers.forEach(x -> Output.showResultWithMoney(
                    x.getName(), x.getBettingMoney() * LOSE_MULTIPLE));
            burstPlayers.forEach(x -> dealerMoney -= x.getBettingMoney() * LOSE_MULTIPLE);
            gamers.diePlayers(burstPlayers);
        }
    }

    public void checkBlackJackWithDealerBlackJack() {
        List<Player> blackJackPlayers = gamers.isBlackJack();
        if (dealer.isBlackJack()) {
            blackJackWithDealer(blackJackPlayers);
            return;
        }

        blackJack(blackJackPlayers);
    }

    public void blackJack(List<Player> blackJackPlayers) {
        if (blackJackPlayers != null) {
            blackJackPlayers.forEach(x -> Output.showResultWithMoney(
                    x.getName(), x.getBettingMoney() * BLACK_JACK_WIN_MULTIPLE));
            blackJackPlayers.forEach(x -> dealerMoney -= x.getBettingMoney() * BLACK_JACK_WIN_MULTIPLE);
            gamers.diePlayers(blackJackPlayers);
        }
    }

    public void blackJackWithDealer(List<Player> blackJackPlayers) {
        if (blackJackPlayers != null) {
            blackJackPlayers.forEach(x -> Output.showResultWithMoney(
                    x.getName(), x.getBettingMoney() * DRAW_MULTIPLE));
            blackJackPlayers.forEach(x -> dealerMoney -= x.getBettingMoney() * DRAW_MULTIPLE);
            gamers.diePlayers(blackJackPlayers);
        }
    }

    public void checkNumberHighScoreThenDealer() {
        List<Player> higherScorePlayers = gamers.isHigherScoreThen(dealer);
        if (higherScorePlayers != null) {
            higherScorePlayers.forEach(x -> Output.showResultWithMoney(
                    x.getName(), x.getBettingMoney() * WIN_MULTIPLE));
            higherScorePlayers.forEach(x -> dealerMoney -= x.getBettingMoney() * WIN_MULTIPLE);
            gamers.diePlayers(higherScorePlayers);
        }
    }

    public void checkNumberSameScoreWithDealer() {
        List<Player> sameScorePlayers = gamers.isSameScoreWith(dealer);
        if (sameScorePlayers != null) {
            sameScorePlayers.forEach(x -> Output.showResultWithMoney(
                    x.getName(), x.getBettingMoney() * DRAW_MULTIPLE));
            sameScorePlayers.forEach(x -> dealerMoney -= x.getBettingMoney() * DRAW_MULTIPLE);
            gamers.diePlayers(sameScorePlayers);
        }
    }

    public void checkNumberLowerScoreThenDealer() {
        if (gamers.getPlayers() != null) {
            gamers.getPlayers().forEach(x -> Output.showResultWithMoney(
                    x.getName(), x.getBettingMoney() * LOSE_MULTIPLE));
            gamers.getPlayers().forEach(x -> dealerMoney -= x.getBettingMoney() * LOSE_MULTIPLE);
        }
    }

    public void showDealerMoney() {
        Output.showResultWithMoney(dealer.getName(), dealerMoney);
    }
}
