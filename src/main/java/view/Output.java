package view;

import domain.user.Gamers;
import domain.user.Player;

import java.text.DecimalFormat;

public class Output {
    static void showGamePlayerInput() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리) 최대 10명");
    }

    static void showBettingMoneyInput(String playerName) {
        System.out.println(playerName + "의 배팅금액은?");
    }

    public static void showInitCardUi(String playersName) {
        System.out.println(playersName + "에게 2장의 카드를 나누었습니다.");
    }

    public static void showGamersInfo(Gamers gamers) {
        System.out.println(gamers);
    }

    public static void showPlayerInfo(Player player) {
        System.out.println(player);
    }

    static void showWantMoreCard(String playerName) {
        System.out.println(playerName + "은(는) 카드를 더받겠습니까?");
    }

    public static void showDealerMoreCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다");
    }

    public static void showGamersResult(Gamers gamers) {
        System.out.println("\n" + gamers.toStringWithResult());
        System.out.println("$$$$$$$$$$$$$$$$$최종수익$$$$$$$$$$$$$$$$$");
    }

    public static void showResultWithMoney(String name, double money) {
        System.out.println(name + ": " + withoutPoint(money));
    }

    private static String withoutPoint(double money) {
        return new DecimalFormat("#.##").format(money);
    }
}
