package domain.gameElement;

import java.util.List;
import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;

public class UsersSumNumbers {
    public void usersSumNumbers(List<User> users, int step) {
        usersSumNumbers((Dealer) users.get(0), step);
        for (int i = 1; i < users.size(); i++) {
            usersSumNumbers((Player) users.get(i), step);
        }
    }

    public int usersSumNumbers(Player player, int step) {
        playerSumNumbers(player, step);
        return player.getSumNumbers();
    }

    public int usersSumNumbers(Dealer dealer, int step) {
        dealerSumNumbers(dealer, step);
        return dealer.getSumNumbers();
    }

    private void dealerSumNumbers(Dealer dealer, int step) {
        List<Card> cards = dealer.getCards();
        if (step == 1) {
            dealer.setSumNumbers(cardsSumNumbers("Dealer", cards.get(cards.size() - 2)));
        }
        dealer.setSumNumbers(cardsSumNumbers("Dealer", cards.get(cards.size() - 1)));
    }

    private void playerSumNumbers(Player player, int step) {
        List<Card> cards = player.getCards();
        if (step == 1) {
            player.setSumNumbers(cardsSumNumbers(player.getName(), cards.get(cards.size() - 2)));
        }
        player.setSumNumbers(cardsSumNumbers(player.getName(), cards.get(cards.size() - 1)));
    }

    private int cardsSumNumbers(String name, Card card) {
        int sumNumbers = card.getCardNumber(name);
        return sumNumbers;
    }
}
