package view;

public class OutputView {

    public static void showInputPlayerNamesInfo(){
        System.out.println("게임에 참여한 사람의 이름을 입력하세요 (쉼표 기준으로 분리)");
    }

    public static void showInputPlyerBettingMoneyInfo(String playerName){
        System.out.printf("%s의 배팅 금액은?\n", playerName);
    }
}
