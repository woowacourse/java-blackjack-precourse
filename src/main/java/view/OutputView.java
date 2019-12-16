package view;

import domain.user.Dealer;
import domain.user.Player;

public class OutputView {
    private static final String NEXT_LINE = "\n";
    private static final String CARD = "님의 카드 : ";
    private static final String DEALER_CARD = "딜러의 카드 : ";
    private static final String DEALER_GET_ONE_MORE = "딜러의 카드의 합이 16보다 낮아 카드를 한 장 더 받았습니다.";
    private static final String RESULT = " 결과 : ";
    private static final String DEALER = "딜러 : ";
    private static final String COLON = " : ";
    private static final String PROFIT = "##최종수익";


    public static void printCards(Player player) {
        System.out.println(player.getName() + CARD + player.getCards().toString());
    }

    public static void printDealerOneCard(Dealer dealer) {
        System.out.println(DEALER_CARD + dealer.getCards().get(0));
    }

    public static void printDealerGetOneMore() {
        System.out.println(NEXT_LINE + DEALER_GET_ONE_MORE);
    }

    public static void printPlayerCardsAndResult(Player player) {
        System.out.println(player.getName() + CARD + player.getCards().toString() + RESULT + player.sumOfCard());
    }

    public static void printDealerCardsAndResult(Dealer dealer) {
        System.out.println(DEALER_CARD + dealer.getCards().toString() + RESULT + dealer.sumOfCard() + NEXT_LINE);
    }

    public static void printPlayerProfit(Player player) {
        System.out.println(PROFIT + NEXT_LINE + player.getName() + COLON + player.getProfit());
    }

    public static void printDealer(double profit) {
        System.out.println(DEALER + profit);
    }
}
