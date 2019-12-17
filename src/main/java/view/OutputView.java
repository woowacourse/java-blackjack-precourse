package view;

import domain.user.Dealer;
import domain.user.Entry;
import domain.user.Player;
import game.GameConstants;
import util.StringUtil;

import java.util.List;

public class OutputView {
    private static final String DEALER = "딜러";
    private static final String MSG_NO_ENTRY = "한 명 이상의 참가자가 필요합니다.";
    private static final String MSG_NO_CARD = "카드가 모두 소진되었습니다. 덱을 재구성합니다.";
    private static final String MSG_BUST = "버스트입니다!";
    private static final String DEALER_CARD = "딜러의 카드 : ";
    private static final String DIVIDE_CARD_FORMAT = "딜러와 %s에게 %d장의 카드를 나누었습니다.";
    private static final String DEALER_CARD_FORMAT = "딜러의 점수가 16 이하라 카드를 추가로 지급받았습니다. 현재 점수 : %d";
    private static final String RESULT = " - 결과 : ";
    private static final String RESULT_MONEY = "\n*** 최종 수익 ***";

    public static void printNoEntry() {
        System.out.println(MSG_NO_ENTRY);
    }

    public static void printNoCard() {
        System.out.println(MSG_NO_CARD);
    }

    public static void printBust() {
        System.out.println(MSG_BUST + "\n");
    }

    public void printDivideResult(Dealer dealer, List<Player> players) {
        System.out.println(
                String.format(DIVIDE_CARD_FORMAT
                        , StringUtil.joinPlayerName(players)
                        , GameConstants.FIRST_DIVIDE_COUNT));
        System.out.println(DEALER_CARD + dealer.getOneCardName());
        players.forEach(player -> System.out.println(player.toString()));
        System.out.println();
    }

    public void printCardState(Entry entry) {
        System.out.println(entry.toString());
    }

    public void printDealerAddState(int count) {
        System.out.println(String.format(DEALER_CARD_FORMAT, count));
    }

    public void printResultOfEntry(Entry entry) {
        System.out.println(entry.toString() + RESULT + entry.getScore());
    }

    public void printResultMoney(Dealer dealer, List<Player> players) {
        System.out.println(RESULT_MONEY);
        System.out.println(DEALER + " " + dealer.getProfits());
        for (Player player : players) {
            System.out.println(player.getName() + " " + player.getProfits());
        }
    }
}
