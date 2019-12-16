package domain.view;

import domain.outcome.Outcomes;
import domain.user.Users;

public class OutputUtil {
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

    public static void printAceScoreQuestion() {
        System.out.println("ACE를 1 또는 11 중 무엇으로 사용할 것인가요?" +
                "\n" +
                "(1, 11 로 대답해주세요!");
    }

    public static void printAddCardQuestion(String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }

    public static void printOutcomes(Outcomes outcomes) {
        System.out.println("\n"
                + outcomes.toString());
    }

    public static void printDealerAddCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
    }


}
