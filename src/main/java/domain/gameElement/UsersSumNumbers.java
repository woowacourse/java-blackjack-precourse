package domain.gameElement;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import java.util.List;

public class UsersSumNumbers {
    public void usersSumNumbers(List<User> users) {
        usersSumNumbers((Dealer) users.get(0));
        for (int i = 1; i < users.size(); i++) {
            usersSumNumbers((Player) users.get(i));
        }
    }

    public int usersSumNumbers(Player player) {
        playerSumNumbers(player);
        return player.getSumNumbers();
    }

    public int usersSumNumbers(Dealer dealer) {
        dealerSumNumbers(dealer);
        return dealer.getSumNumbers();
    }

    private void dealerSumNumbers(Dealer dealer) {
        List<Card> cards = dealer.getCards();
        dealer.setSumNumbers(cardsSumNumbers("Dealer", cards));
    }

    private void playerSumNumbers(Player player) {
        List<Card> cards = player.getCards();
        player.setSumNumbers(cardsSumNumbers("Player", cards));
    }

    private int cardsSumNumbers(String name, List<Card> cards) {
        int sumNumbers = 0;
        for (Card card : cards) {
            sumNumbers += card.getCardNumber(name);
        }
        return sumNumbers;
    }
}
