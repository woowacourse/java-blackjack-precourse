package domain.gameElement;

import java.util.List;
import domain.user.User;
import domain.user.Dealer;
import domain.user.Player;

public class UsersTotal {
    public void usersTotalCards(List<User> users) {
        userTotalCards((Dealer) users.get(0));
        for (int i = 1; i < users.size(); i++) {
            userTotalCards((Player) users.get(i));
        }
    }

    private void userTotalCards(Dealer dealer) {
        System.out.println();
        dealer.userCardsInfo(dealer.getCards(), "딜러");
        System.out.println("    - 결과 : " + dealer.getSumNumbers());
    }

    private void userTotalCards(Player player) {
        player.userCardsInfo(player.getCards(), player.getName());
        System.out.println("    - 결과 : " + player.getSumNumbers());
    }

    public void usersTotalProfit(List<User> users) {
        System.out.println("\n## 최종수익");
        usersTotalProfit((Dealer) users.get(0));
        for (int i = 1; i < users.size(); i++) {
            usersTotalProfit((Player) users.get(i));
        }
    }

    private void usersTotalProfit(Dealer dealer) {
        System.out.println("딜러 : " + dealer.getProfit());
    }

    private void usersTotalProfit(Player player) {
        System.out.println(player.getName() + " : " + player.getProfit());
    }
}
