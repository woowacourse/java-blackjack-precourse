package view;

import domain.outcome.Outcomes;
import view.dto.OutcomesConverter;

public class OutputUtil {
    private static final String CARD_STRING = " 카드: ";
    private static final String RESULT_STRING = " - 결과: ";
    private static final String DEALER_STRING = "딜러: ";

    public static void printUsersNameDemand() {
        System.out.println("게임에 참가할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void printBettingMoneyDemand(String user) {
        System.out.println(user + " 의 배팅 금액은?");
    }

    public static void printDevideMessage(String usersName) {
        System.out.println("딜러와 " +
                usersName +
                "에게 2장을 나눴습니다.");
    }

    public static void printAddCardQuestion(String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }

    public static void printOutcomes(Outcomes outcomes) {
        System.out.println("\n"
                + "## 최종 수익\n"
                + OutcomesConverter.convertOutcomesString(outcomes.getOutcomes()));
    }

    public static void printDealerAddCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
    }

    public static void printPlayerState(String name, String cardsStr) {
        System.out.println(name + CARD_STRING + cardsStr);
    }

    public static void printPlayerState(String name, String cardsStr, int score) {
        System.out.println(name
                + CARD_STRING
                + cardsStr
                + RESULT_STRING
                + score);
    }

    public static void printDealerState(String cardsStr) {
        System.out.println(DEALER_STRING + cardsStr);
    }

    public static void printDealerFinalOutput(String cardsStr, int score) {
        System.out.println(DEALER_STRING + cardsStr + RESULT_STRING + score);
    }

}