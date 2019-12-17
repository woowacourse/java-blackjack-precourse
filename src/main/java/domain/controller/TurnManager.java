package domain.controller;

import domain.user.Dealer;
import domain.user.Player;
import domain.view.Announcer;

import java.util.Scanner;

public class TurnManager {
    Scanner scanner;
    CardManager cardManager;
    Announcer announcer;
    ScoreManager scoreManager;

    final static int MAKE_ONE_CARD = 1;

    public TurnManager() {
        scanner = new Scanner(System.in);
        cardManager = new CardManager();
        announcer = new Announcer();
        scoreManager = new ScoreManager();
    }

    private void giveOneMoreCard(Player player) {
        char yesOrNo;
        announcer.announceOneMoreCard(player);
        yesOrNo = scanner.next().charAt(0);
        if (yesOrNo == 'Y' || yesOrNo == 'y') {
            announcer.announceDoneGiveCard(player);
            cardManager.giveCard(player, MAKE_ONE_CARD);
        }
    }

    private void giveOneMoreCard(Dealer dealer) {
        if (scoreManager.isLessThanSixTeen(
                scoreManager.getSumScore(dealer))) {
            cardManager.giveCard(dealer, MAKE_ONE_CARD);
            announcer.announceCardStatus(dealer);
        }

    }

    public void nextTurn(Dealer dealer, Player[] players) {
        giveOneMoreCard(dealer);
        for (Player player : players) {
            giveOneMoreCard(player);
        }
    }
}
