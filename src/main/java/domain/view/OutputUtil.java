package domain.view;

public class OutputUtil {
    public static void printUsersNameDemand() {
        System.out.println("게임에 참가할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void printBettingMoneyDemand(String user){
        System.out.println(user + " 의 배팅 금액은?");
    }

    public static void printDevideMessage(String[] names) {
        System.out.println("딜러와 " +
                String.join(",", names) +
                "에게 2장을 나눴습니다.");
    }
}
