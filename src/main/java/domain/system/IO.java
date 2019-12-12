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
        System.out.println();
    }

    public static Boolean haveMoreCard(BlackJackPlayer player) {
        String input;
        String inputPattern = "y|n";
        String question = player.toString() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";

        input = getInputUntilValid(question, inputPattern);
        if (input.equals("y")) {
            return true;
        }
        return false;
    }

    public static void printDealerHaveMore() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        System.out.println();
    }

    public static void printPlayerCard(BlackJackPlayer player) {
        String name = player.toString();
        String cardsName = player.getCardsName();

        System.out.println(name + ": " + cardsName);
    }

    public static void printFinalEarn() {
        System.out.println("## 최종수익");
   }

    public static void printEarn(String name, double earn) {
        System.out.println(name + ": " + earn);
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
