package domain.system;

import domain.user.BlackJackPlayer;
import domain.user.Dealer;
import domain.user.Player;

import java.util.List;
import java.util.Scanner;

/**
 * 입출력을 담당하는 객체
 */
public class IO {
    private static final Scanner scan = new Scanner(System.in);

    private static final String INVALID_INPUT = "";

    public static String getPlayersNames() {
        String playersNamePattern = "([a-zA-Z][a-zA-Z]{0,9}(,|$))+";
        String question = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";

        return getInputUntilValid(question, playersNamePattern);
    }

    public static int getPlayerBettingMoney(String name) {
        String bettingMoneyPattern = "^[1-9][0-9]*";
        String question = name + "의 배팅 금액은?";

        return Integer.parseInt(getInputUntilValid(question, bettingMoneyPattern));
    }

    public static void printGiveCardsFirst(List<Player> players, Dealer dealer) {
        System.out.println("딜러와 "
                + players.toString().replace("[", "").replace("]", "")
                + "에게 2장씩 나누었습니다.");
        printPlayersCard(players, dealer);
    }

    public static void printPlayersCard(List<Player> players, Dealer dealer) {
        printPlayerCard(dealer);
        for (Player player : players) {
            printPlayerCard(player);
        }
    }

    private static void printPlayerCard(BlackJackPlayer player) {
        String name = player.getName();
        String cardsName = player.getCardsName();

        System.out.println(name + ": " + cardsName);
    }

    private static String getInputUntilValid(String question, String validPattern) {
        String input = INVALID_INPUT;

        while (!input.matches(validPattern)) {
            input = getInput(question);
        }
        return input;
    }

    private static String getInput(String question) {
        System.out.println(question);
        return scan.nextLine();
    }
}
