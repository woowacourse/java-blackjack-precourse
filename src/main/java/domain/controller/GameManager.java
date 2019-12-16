package domain.controller;

import domain.user.Dealer;
import domain.user.Player;
import domain.view.PlayerStatusAnnouncer;

public class GameManager {
    UserCreateManager userCreateManager;
    PlayerStatusAnnouncer gameStatus;
    ScoreManager scoreManager;
    Player[] players;
    Dealer dealer;

    final static String DEALER_NAME = "딜러";

    public GameManager() {
        dealer = new Dealer(DEALER_NAME);
        userCreateManager = new UserCreateManager(dealer);
        gameStatus = new PlayerStatusAnnouncer();
        scoreManager = new ScoreManager();
    }

    private void afterFirstTurn() {
        if (scoreManager.checkBlackJack(dealer)) {
            System.exit(0);
        }

        if (scoreManager.checkBlackJack(players)) {
            System.exit(0);
        }
    }

    public void run() {
        players = userCreateManager.playerStandBy();
        gameStatus.afterStandByUser(dealer, players);
        afterFirstTurn();
    }
}
