package domain.view;

import domain.controller.ScoreManager;
import domain.user.Dealer;
import domain.user.Player;

public class GameStatusAnnouncer {
    Announcer announce;
    ScoreManager scoreManager;

    public GameStatusAnnouncer() {
        announce = new Announcer();
        scoreManager = new ScoreManager();
    }

    private void printCardStatus(Dealer dealer, Player[] players) {
        announce.announceCardStatus(dealer);
        announce.announceCardStatus(players);
    }

    public void afterStandByUser (Dealer dealer, Player[] players){
        announce.announceDoneUserCreate(players);
        printCardStatus(dealer, players);
    }

    public void afterTurn (Dealer dealer, Player[] players){
        announce.announceDoneUserCreate(players);
        printCardStatus(dealer, players);
    }

}
