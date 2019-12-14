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

}
