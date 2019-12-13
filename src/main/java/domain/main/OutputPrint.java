package domain.main;

public class OutputPrint {

    private void print(String message) {
        System.out.print(message);
    }

    private void println(String message) {
        System.out.println(message);
    }

    public void getPlayerNames() {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public void getBettingMoney(String playerName) {
        println(playerName + "의 배팅 금액은?");
    }

}
