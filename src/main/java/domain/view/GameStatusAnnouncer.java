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

    public void gameOver(Dealer dealer, Player[] players) {
        printCardWithScore(dealer, players);
        System.exit(0);
    }

    private void printCardStatus(Dealer dealer, Player[] players) {
        announce.announceCardStatusStart();
        announce.announceCardStatus(dealer);
        announce.announceCardStatus(players);
        System.out.println();
    }

    private void printCardWithScore(Dealer dealer, Player[] players) {
        announce.announceCardStatusStart();
        announce.announceScore(dealer, scoreManager.getSumScore(dealer));
        announce.announceScore(players, scoreManager);
    }

    public void afterStandByUser (Dealer dealer, Player[] players){
        announce.announceDoneUserCreate(players);
        printCardStatus(dealer, players);
    }

    public void afterTurn (Dealer dealer, Player[] players){
        printCardStatus(dealer, players);
    }
}
