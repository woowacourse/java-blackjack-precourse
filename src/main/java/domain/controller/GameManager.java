package domain.controller;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import domain.view.GameStatusAnnouncer;

public class GameManager {
    UserCreateManager userCreateManager;
    TurnManager turnManager;
    GameStatusAnnouncer gameStatus;
    ScoreManager scoreManager;
    Player[] players;
    Dealer dealer;

    final static String DEALER_NAME = "딜러";

    public GameManager() {
        dealer = new Dealer(DEALER_NAME);
        userCreateManager = new UserCreateManager(dealer);
        turnManager = new TurnManager();
        gameStatus = new GameStatusAnnouncer();
        scoreManager = new ScoreManager();
    }

    private void checkPlayers() {
        if (scoreManager.checkBiggerThanBlackJack(players)) {
            gameStatus.gameOver(dealer,players);
        }
        if (scoreManager.checkBlackJack(players)) {
            gameStatus.gameOver(dealer,players);
        }
    }

    private void afterGiveCard() {
        if (scoreManager.checkBlackJack(dealer)) {
            gameStatus.gameOver(dealer,players);
        }
        checkPlayers();
    }

    private void repeatUntilBLackJackOrMore() {
        while (true) {
            turnManager.nextTurn(dealer, players);
            gameStatus.afterTurn(dealer, players);
            afterGiveCard();
        }
    }

    public void run() {
        players = userCreateManager.playerStandBy();
        gameStatus.afterStandByUser(dealer, players);
        afterGiveCard();
        repeatUntilBLackJackOrMore();
    }
}