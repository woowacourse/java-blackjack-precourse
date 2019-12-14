package controller;

import domain.user.Dealer;
import domain.user.Gamers;
import domain.user.Player;

import java.util.List;

public class StakeManager {
    private Gamers gamers;
    private Dealer dealer;

    public StakeManager(Gamers gamers) {
        this.gamers = gamers;
        this.dealer = gamers.findDealer();
        gamers.getPlayers().remove(dealer);
    }

}
