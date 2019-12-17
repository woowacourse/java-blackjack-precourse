package domain.controller;

import domain.user.Player;
import domain.view.Announcer;

import java.util.Scanner;

public class TurnManager {
    Scanner scanner;
    CardManager cardManager;
    Announcer announcer;

    final static int MAKE_ONE_CARD = 1;

    public TurnManager() {
        scanner = new Scanner(System.in);
        cardManager = new CardManager();
        announcer = new Announcer();
    }

    private void giveOneMoreCard(Player player) {
        char yesOrNo;
        announcer.announceOneMoreCard(player);
        yesOrNo = scanner.next().charAt(0);
        System.out.println(yesOrNo);
        if (yesOrNo == 'Y' || yesOrNo == 'y') {
            cardManager.giveCard(player, MAKE_ONE_CARD);
        }
    }

    public void nextTurn(Player[] players) {
        for (Player player : players) {
            giveOneMoreCard(player);
        }
    }
}
