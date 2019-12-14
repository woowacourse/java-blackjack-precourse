package controller;

import domain.user.Dealer;
import domain.user.Gamers;
import domain.user.Player;
import view.Output;

import java.util.List;

public class StakeManager {
    private Gamers gamers;
    private Dealer dealer;

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
    }

    public void checkBurst() {
        List<Player> burstPlayers = gamers.isBurst();
        if(burstPlayers != null){
            burstPlayers.forEach(Output::showLoseResult);
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
        if(blackJackPlayers != null){
            blackJackPlayers.forEach(Output::showBlackJackWinResult);
            gamers.diePlayers(blackJackPlayers);
        }
    }

    public void blackJackWithDealer(List<Player> blackJackPlayers) {
        if(blackJackPlayers != null){
            blackJackPlayers.forEach(Output::showDrawResult);
            gamers.diePlayers(blackJackPlayers);
        }
    }

    public void checkNumberHighScoreThenDealer() {
        List<Player> higherScorePlayers = gamers.isHigherScoreThen(dealer);
        if(higherScorePlayers != null) {
            higherScorePlayers.forEach(Output::showWinResult);
            gamers.diePlayers(higherScorePlayers);
        }
    }

    public void checkNumberSameScoreWithDealer() {
        List<Player> sameScorePlayers = gamers.isSameScoreWith(dealer);
        if(sameScorePlayers != null) {
            sameScorePlayers.forEach(Output::showDrawResult);
            gamers.diePlayers(sameScorePlayers);
        }
    }

    public void checkNumberLowerScoreThenDealer() {
        if(gamers.getPlayers() != null) {
            gamers.getPlayers().forEach(Output::showLoseResult);
        }
    }
}
