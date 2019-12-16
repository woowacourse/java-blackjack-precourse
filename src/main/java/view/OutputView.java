package view;

import domain.user.Dealer;
import domain.user.Player;

public class OutputView {
    public static void printCards(Player player) {
        System.out.println(player.getName()+ "님의 카드 : " + player.getCards().toString());
    }

    public static void printDealerOneCard(Dealer dealer) {
        System.out.println("딜러의 카드 : " + dealer.getCards().get(0));
    }
}
