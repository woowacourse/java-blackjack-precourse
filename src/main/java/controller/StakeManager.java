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

    public void checkBurst() {
        List<Player> burstPlayers = gamers.isBurst();
        burstPlayers.forEach(Output::showLoseResult);
        gamers.diePlayers(burstPlayers);
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
        blackJackPlayers.forEach(Output::showBlackJackWinResult);
        gamers.diePlayers(blackJackPlayers);
    }

    public void blackJackWithDealer(List<Player> blackJackPlayers) {
        blackJackPlayers.forEach(Output::showDrawResult);
        gamers.diePlayers(blackJackPlayers);
    }
}
