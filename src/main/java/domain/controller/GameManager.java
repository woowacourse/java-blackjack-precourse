package domain.controller;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import domain.view.GameStatusAnnouncer;

public class GameManager {
    UserCreateManager userCreateManager;
    GameStatusAnnouncer gameStatus;
    ScoreManager scoreManager;
    Player[] players;
    Dealer dealer;

    final static String DEALER_NAME = "딜러";

    public GameManager() {
        dealer = new Dealer(DEALER_NAME);
        userCreateManager = new UserCreateManager(dealer);
        gameStatus = new GameStatusAnnouncer();
        scoreManager = new ScoreManager();
    }

    private void afterGiveCard() {
        if (scoreManager.checkBlackJack(dealer, players)) {
            System.exit(0);
        }
    }


    public void run() {
        players = userCreateManager.playerStandBy();
        gameStatus.afterStandByUser(dealer, players);
        afterGiveCard();
    }
}
