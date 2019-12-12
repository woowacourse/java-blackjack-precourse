package woowacourse.blackjack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BlackJackGame {
    private Scanner sc = new Scanner(System.in);

    public void runGame() {
        List<String> playerNames = this.getPlayerNames();
        List<Double> bettingMoney = this.getBettingMoney(playerNames);
    }

    private List<String> getPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return Arrays.asList(sc.nextLine().split(","));
    }

    private List<Double> getBettingMoney(List<String> playerNames) {
        List<Double> bettingMoney = new ArrayList<>();
        for (String playerName : playerNames) {
            System.out.println("" + playerName + "의 배팅 금액은?");
            bettingMoney.add(sc.nextDouble());
            System.out.println();
        }
        return bettingMoney;
    }
}
