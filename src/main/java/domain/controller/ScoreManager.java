package domain.controller;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;

import java.util.Scanner;

public class ScoreManager {
    Scanner scanner;
    CardManager cardManager;

    final static int BLACKJACK = 21;
    final static int ACE_ELEVEN_MODE = 10;
    final static String ACE_TYPE = "ACE";

    public ScoreManager() {
        scanner = new Scanner(System.in);
        cardManager = new CardManager();
    }

    public boolean checkBlackJack(Dealer dealer) {
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

    private boolean isBlackJack(User user) {
        if (getSumScore(user) == BLACKJACK) {
            System.out.printf("%s이 블랙잭을 달성했습니다!\n", user.getName());
            return true;
        }
        return false;
    }

    public int getSumScore(User user) {
        int score = user.getCards().stream()
                .mapToInt(Card::getSymbolByScore).sum();

        if (isContainAce(user)
                && ((score + ACE_ELEVEN_MODE) <= BLACKJACK)) {
            return score + ACE_ELEVEN_MODE;
        }
        return score;
    }


    private boolean isContainAce(User user) {
        boolean isContain = false;
        for (Card card : user.getCards()) {
            isContain = card.getSymbolByString().equals(ACE_TYPE);
        }
        return isContain;
    }

    private boolean isBiggerThanBlackJack(User user) {
        int score = user.getCards().stream()
                .mapToInt(Card::getSymbolByScore).sum();
        if (score > BLACKJACK) {
            return true;
        }
        return false;
    }

}
