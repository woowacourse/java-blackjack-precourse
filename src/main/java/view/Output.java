package view;

import domain.user.Gamers;
import domain.user.Player;

import java.util.List;
import java.util.stream.Collectors;

public class Output {
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
}
