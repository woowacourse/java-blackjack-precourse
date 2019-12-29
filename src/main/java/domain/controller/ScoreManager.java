package domain.controller;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import domain.view.Announcer;

import java.util.Scanner;

public class ScoreManager {
    Scanner scanner;
    CardManager cardManager;
    Announcer announcer;

    final static int BLACKJACK = 21;
    final static int SIXTEEN = 16;
    final static int ACE_ELEVEN_MODE = 10;
    final static String ACE_TYPE = "ACE";

    public ScoreManager() {
        scanner = new Scanner(System.in);
        cardManager = new CardManager();
        announcer = new Announcer();
    }

    public boolean isLessThanSixTeen(int score) {
        if (score < 16) {
            return true;
        }
        return false;
    }

    public boolean checkBlackJack(Dealer dealer) {
        if (isBiggerThanBlackJack(dealer)) {
            return true;
        }

        if (isBlackJack(dealer)) {
            return true;
        }
        return false;
    }

    public boolean checkBlackJack(Player[] players) {
        boolean flag = false;
        for (Player player : players) {
            flag = isBlackJack(player);
        }
        return flag;
    }

    public boolean checkBiggerThanBlackJack(Player[] players) {
        boolean flag = false;
        for (Player player : players) {
            flag = isBiggerThanBlackJack(player);
        }
        return flag;
    }

    private boolean isBlackJack(User user) {
        if (getSumScore(user) == BLACKJACK) {
            announcer.announceEndByBlackJack(user);
            return true;
        }
        return false;
    }

    public int getSumScore(User user) {
        int score = user.getCards()
                .stream().mapToInt(Card::getSymbolByScore).sum();

        if (isContainAce(user)
                && ((score + ACE_ELEVEN_MODE) <= BLACKJACK)) {
            return score + ACE_ELEVEN_MODE;
        }
        return score;
    }


    private boolean isContainAce(User user) {
        return user.getCards().stream().anyMatch((card) -> card.getSymbolByString().equals(ACE_TYPE));
    }

    private boolean isBiggerThanBlackJack(User user) {
        int score = getSumScore(user);
        if (score > BLACKJACK) {
            announcer.announceOverBlackJack(user, getSumScore(user));
            return true;
        }
        return false;
    }

}
