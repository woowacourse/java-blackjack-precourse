package view;

import domain.user.Dealer;
import domain.user.Player;

/**
 * @author KIMSIYOUNG
 * @apiNote 출력을 담당하는 클래스로, GameModel 에서 출력이 필요한 부분을 담당합니다.
 * @apiNote 모델에 넣는 것 보다, 가독성이 좋은 것 같아 따로 클래스로 분리하였습니다.
 * @since 2019-12-13
 */
public class OutputView {
    private static final String NEXT_LINE = "\n";
    private static final String CARD = "님의 카드 : ";
    private static final String DEALER_CARD = "딜러의 카드 : ";
    private static final String DEALER_GET_ONE_MORE = "딜러의 카드의 합이 16보다 낮아 카드를 한 장 더 받았습니다.";
    private static final String RESULT = " 결과 : ";
    private static final String DEALER = "딜러 : ";
    private static final String COLON = " : ";
    private static final String PROFIT = "##최종수익";
    private static final int DEALER_FIRST_CARD_INDEX = 0;

    public static void printCards(Player player) {
        System.out.println(player.getName() + CARD + player.getCards().toString());
    }

    public static void printDealerOneCard(Dealer dealer) {
        System.out.println(DEALER_CARD + dealer.getCards().get(DEALER_FIRST_CARD_INDEX));
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
