package domain.controller;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;

public class ScoreManager {
    final static int BLACKJACK = 21;

    public boolean checkBlackJack(Dealer dealer, Player[] players) {
        if (isBlackJack(dealer)) {
            return true;
        }

        for (Player player : players) {
            return isBlackJack(player);
        }

        return false;
    }

    private boolean isBlackJack(User user) {
        int score = user.getCards().stream().mapToInt(Card::getSymbolByScore).sum();
        if (score == BLACKJACK) {
            return true;
        }
        return false;
    }

    private boolean isBiggerThanBlackJack(User user) {
        int score = user.getCards().stream().mapToInt(Card::getSymbolByScore).sum();
        if (score > BLACKJACK) {
            return true;
        }
        return false;
    }

}
