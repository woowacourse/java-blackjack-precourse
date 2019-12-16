package ui;

public class Output {
    public static void displayForGetPlayerNames() {
        System.out.println("게임에 참여할 사람들의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void displayForGetPlayerBettingMoney(String playerName) {
        System.out.println(playerName + "의 배팅금액은?");
    }

    public static void displayForInitInformation(String playerNames) {
        System.out.println(playerNames + "에게 2장의 카드를 나누었습니다.");
    }

    public static void displayForAskPickMoreCard(String playerName) {
        System.out.println(playerName + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }

    public static void displayDealerPickInfo() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }
}
