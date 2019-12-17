/**
 * Copyright (c) 2019 Seungwan Park,
 * All rights reserved.
 */

package view;

import domain.model.user.Dealer;
import domain.model.user.Player;

import java.util.HashMap;

/**
 * @author Seungwan Park, github.com/toneyparky
 * @version 1.0
 * @apiNote 모든 출력을 담당하는 클래스입니다.
 * @since : 2019.12.17 화요일
 */
public class PrintController {
    private static final String ASKING_PLAYER_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_IS_EMPTY_ERROR = "오류 : 아무것도 입력하지 않으셨습니다.";
    private static final String INPUT_HAS_BLANK_ERROR = "오류 : 입력에 공백이 있습니다.";
    private static final String INPUT_IS_COMMA_ERROR = "오류 : 컴마만 입력이 되었습니다.";
    private static final String ASKING_BETTING_MONEY = "의 배팅 금액은?";
    private static final String INPUT_NUMBER_IS_SMALL_ERROR = "오류 : 1 이상의 정수만 입력해주세요.";
    private static final String DEALER_AND = "딜러와 ";
    private static final String HANDING_TWO_CARDS_INFORMATION = "에게 2장의 카드를 나누었습니다.";
    private static final String DEALER_COLON = "딜러: ";
    private static final String CARD_COLON = "카드: ";
    private static final String ASK_DRAW_MORE_CARD = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String INPUT_IS_NOT_Y_OR_N_ERROR = "y나 n만 입력해주세요.";
    private static final String PRINT_DEALER_CAN_NOT_DRAW_MORE_CARD = "딜러는 17이상이라 카드를 받지 않습니다.";
    private static final String DEALER_IS = "딜러는 ";
    private static final String LOWER_THAN_SIXTEEN = "16이하라 ";
    private static final String GET_N_NUMBER_CARDS = "장의 카드를 더 받았습니다.";
    private static final String DEALER_CARD_COLON = "딜러 카드: ";
    private static final String RESULT_COLON = " - 결과: ";
    private static final String TOTAL_PROFIT = "## 최종 수익";
    private static final String COLON = ": ";
    private static final String LINE_BREAKER = "\n";
    private static final int SEVEN = 7;
    private static final String MANY = "여러";

    private static HashMap<Integer, String> integerToKoreanNumberHashMap = new HashMap<>();

    public static void makeHashMap() {
        integerToKoreanNumberHashMap.put(1, "한");
        integerToKoreanNumberHashMap.put(2, "두");
        integerToKoreanNumberHashMap.put(3, "세");
        integerToKoreanNumberHashMap.put(4, "네");
        integerToKoreanNumberHashMap.put(5, "다섯");
        integerToKoreanNumberHashMap.put(6, "여섯");
    }

    public static String integerToKoreanNumber(int number) {
        makeHashMap();
        if (number < SEVEN) {
            return integerToKoreanNumberHashMap.get(number);
        }
        return MANY; // 딜러가 추가로 카드를 7장 이상 뽑을 가능성은 무척 낮기에 다 적기보다 여러장으로 표기한다.
    }

    public static void askPlayerName() {
        System.out.println(ASKING_PLAYER_NAME);
    }

    public static void printInputEmptyError() {
        System.out.println(INPUT_IS_EMPTY_ERROR);
    }

    public static void printInputHasBlankError() {
        System.out.println(INPUT_HAS_BLANK_ERROR);
    }

    public static void printInputIsCommaError() {
        System.out.println(INPUT_IS_COMMA_ERROR);
    }

    public static void askBettingMoney(String playerName) {
        System.out.println(LINE_BREAKER + playerName + ASKING_BETTING_MONEY);
    }

    public static void printInputNumberIsSmallError() {
        System.out.println(INPUT_NUMBER_IS_SMALL_ERROR);
    }

    public static void printHandingTwoCardsInformation(String playersName) {
        System.out.println(LINE_BREAKER + DEALER_AND + playersName + HANDING_TWO_CARDS_INFORMATION);
    }

    public static void printDealerCardInformation(Dealer dealer) {
        System.out.println(DEALER_COLON + dealer.getCard().toString());
    }

    public static void printPlayerCardInformation(Player player) {
        System.out.println(player.getName() + CARD_COLON + player.getCardsInformation());
    }

    public static void askDrawMoreCard(Player player) {
        System.out.println(player.getName() + ASK_DRAW_MORE_CARD);
    }

    public static void printInputIsNotYOrNError() {
        System.out.println(INPUT_IS_NOT_Y_OR_N_ERROR);
    }

    public static void printDealerCanNotDrawMoreCard() {
        System.out.println(PRINT_DEALER_CAN_NOT_DRAW_MORE_CARD);
    }

    public static void printDealerExtraCards(int extraCounts) {
        System.out.println(LINE_BREAKER + DEALER_IS + LOWER_THAN_SIXTEEN
                + integerToKoreanNumber(extraCounts) + GET_N_NUMBER_CARDS);
    }

    public static void printDealerCardFinalInformation(Dealer dealer) {
        System.out.println(LINE_BREAKER + DEALER_CARD_COLON
                + dealer.getCardsInformation()
                + RESULT_COLON + dealer.getCurrentScore());
    }

    public static void printPlayerCardFinalInformation(Player player) {
        System.out.println(player.getName() + CARD_COLON
                + player.getCardsInformation()
                + RESULT_COLON + player.getCurrentScore());
    }

    public static void printFinalProfit() {
        System.out.println(LINE_BREAKER + TOTAL_PROFIT);
    }

    public static void printDealerProfit(double dealerProfit) {
        System.out.println(DEALER_COLON + (int) dealerProfit);
    }

    public static void printPlayerProfit(double playerProfit, Player player) {
        System.out.println(player.getName() + COLON + (int) playerProfit);
    }

    public static void printBlankLine() {
        System.out.print(LINE_BREAKER);
    }

}
