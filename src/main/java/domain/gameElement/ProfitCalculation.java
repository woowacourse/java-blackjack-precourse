package domain.gameElement;

import java.util.List;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;

public class ProfitCalculation {
    public void countProfit(List<User> users, int step) {
        for (int i = 1; i < users.size(); i++) {
            countProfit((Dealer) users.get(0), (Player) users.get(i), step);
        }
    }

    private void countProfit(Dealer dealer, Player player, int step) {
        if (step == 1) {
            countProfitBlackJack(dealer, player);
            return;
        }
        if (!countProfitBust(dealer, player)) {
            countProfitTwentyOne(dealer, player);
        }
    }

    private void countProfitBlackJack(Dealer dealer, Player player) {
        if (dealer.getSumNumbers() == 21 && player.getSumNumbers() != 21) {
            System.out.println("딜러의 블랙잭!!");
            dealerWinPlayerProfit(dealer, player);
        } else if (dealer.getSumNumbers() != 21 && player.getSumNumbers() == 21) {
            System.out.println(player.getName() + "님의 블랙잭!!");
            playerBlackJackProfit(dealer, player);
        } else if (dealer.getSumNumbers() != 21 && player.getSumNumbers() != 21) {
            countProfit(dealer, player, 2);
        }
    }

    private void countProfitTwentyOne(Dealer dealer, Player player) {
        if (Math.abs(21 - dealer.getSumNumbers()) < Math.abs(21 - player.getSumNumbers())) {
            dealerWinPlayerProfit(dealer, player);
        } else if (Math.abs(21 - dealer.getSumNumbers()) > Math.abs(21 - player.getSumNumbers())) {
            dealerLosePlayerProfit(dealer, player);
        }
    }

    private boolean countProfitBust(Dealer dealer, Player player) {
        if (dealer.getSumNumbers() > 21 && player.getSumNumbers() <= 21) {
            dealerLosePlayerProfit(dealer, player);
            return true;
        } else if (player.getSumNumbers() > 21 && dealer.getSumNumbers() <= 21) {
            dealerWinPlayerProfit(dealer, player);
            return true;
        } else if (dealer.getSumNumbers() > 21 && player.getSumNumbers() > 21) {
            return true;
        }
        return false;
    }

    private void dealerWinPlayerProfit(Dealer dealer, Player player) {
        dealer.setProfit(player.getBettingMoney());
        player.setProfit(-player.getBettingMoney());
    }

    private void dealerLosePlayerProfit(Dealer dealer, Player player) {
        dealer.setProfit(-player.getBettingMoney());
        player.setProfit(player.getBettingMoney());
    }

    private void playerBlackJackProfit(Dealer dealer, Player player) {
        dealer.setProfit(-player.getBettingMoney() * 1.5);
        player.setProfit(player.getBettingMoney() * 1.5);
    }
}
