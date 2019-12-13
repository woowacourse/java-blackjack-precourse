package domain.main;

import domain.user.Player;

import java.util.List;

public class OutputPrint {

    public void getPlayerNames() {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public void getBettingMoney(String playerName) {
        println(playerName + "의 배팅 금액은?");
    }

    public void giveCardsFirstToPlayers(List<Player> playerList) {
        String nameList ="딜러와 "+playerList.get(0).getName();
        for (Player player : playerList) {
            nameList += ", " + player;
        }

        println(nameList+"에게 2장의 카드를 나누어주었습니다.");
    }

    public void wantMoreCards(String playerName) {
        println(playerName+"는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n");
    }

    public void dealerLessThan16() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void finalReturn() {
        println("## 최종 수익");
    }

    private void print(String message) {
        System.out.print(message);
    }

    private void println(String message) {
        System.out.println(message);
    }

}
