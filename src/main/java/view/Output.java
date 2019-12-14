package view;

import domain.user.Gamers;
import domain.user.Player;

import java.util.List;
import java.util.stream.Collectors;

public class Output {
    private static final double BLACK_JACK_WIN_MULTIPLE = 1.5D;
    private static final int WIN_MULTIPLE = 1;
    private static final int LOSE_MULTIPLE = -1;
    private static final int DRAW_MULTIPLE = 0;

    public static void showGamePlayerInput() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void showBettingMoneyInput(String playerName) {
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

    public static void showWantMoreCard(String playerName) {
        System.out.println(playerName + "은(는) 카드를 더받겠습니까?");
    }

    public static void showDealerMoreCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다");
    }

    public static void showGamersResult(Gamers gamers) {
        System.out.println(gamers.toStringWithResult());
    }

    public static void resultProxy(Player player) {
        System.out.print(player.getName() + ": ");
    }

    public static void showBlackJackWinResult(Player player) {
        resultProxy(player);
        System.out.println(player.getBettingMoney() * BLACK_JACK_WIN_MULTIPLE);
    }

    public static void showDrawResult(Player player) {
        resultProxy(player);
        System.out.println(player.getBettingMoney() * DRAW_MULTIPLE);
    }

    public static void showWinResult(Player player) {
        resultProxy(player);
        System.out.println(player.getBettingMoney() * WIN_MULTIPLE);
    }

    public static void showLoseResult(Player player) {
        resultProxy(player);
        System.out.println(player.getBettingMoney() * LOSE_MULTIPLE);
    }
}
