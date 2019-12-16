package view;

import domain.user.Dealer;
import domain.user.Player;

public class OutputView {
    private static final String NEXT_LINE = "\n";
    private static final String CARD = "님의 카드 : ";
    private static final String DEALER_CARD = "딜러의 카드 : ";
    private static final String DEALER_GET_ONE_MORE = "딜러의 카드의 합이 16보다 낮아 카드를 한 장 더 받았습니다.";


    public static void printCards(Player player) {
        System.out.println(player.getName()+ CARD + player.getCards().toString());
    }

    public static void printDealerOneCard(Dealer dealer) {
        System.out.println(DEALER_CARD + dealer.getCards().get(0));
    }

    public static void printDealerGetOneMore() {
        System.out.println(NEXT_LINE+ DEALER_GET_ONE_MORE);
    }
}
