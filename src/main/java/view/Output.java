package view;

public class Output {
    public static void showGamePlayerInput() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void showBettingMoneyInput(String playerName) {
        System.out.println(playerName + "의 배팅금액은?");
    }
}
