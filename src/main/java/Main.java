import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Player;
import domain.user.PlayerManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Card> cards = CardFactory.provideShuffledCards();
        ArrayList<Player> players = initPlayersWithInput(scanner);
        PlayerManager playerManager = new PlayerManager();

        playerManager.addAll(players);

        /* Debug */
        for (Card card: cards) {
            System.out.println(card.toString());
        }

        System.out.println(playerManager.bettingInfos());
    }

    public static ArrayList<Player> initPlayersWithInput(Scanner scanner) {
        ArrayList<Player> players = new ArrayList<>();
        String[] names = inputNames(scanner);
        ArrayList<Double> moneyList = inputBettings(scanner, names);

        for (int i = 0; i < names.length; i++) {
            players.add(new Player(names[i], moneyList.get(i)));
        }
        return players;
    }

    public static ArrayList<Double> inputBettings(Scanner scanner, String[] names) {
        ArrayList<Double> moneyList = new ArrayList<>();
        for (String name: names) {
            double nextBettingAmount = inputBettingAmount(scanner, name);
            moneyList.add(nextBettingAmount);
        }
        return moneyList;
    }

    public static double inputBettingAmount(Scanner scanner, String name) {
        double bettingAmount;
        System.out.println(name + "의 배팅 금액은?");
        bettingAmount = scanner.nextDouble();

        while (bettingAmount <= 0) {
            System.out.println("배팅 금액은 0보다 커야합니다.");
            System.out.println(name + "의 배팅 금액은?");
            bettingAmount = scanner.nextDouble();
        }

        return bettingAmount;
    }

    public static String[] inputNames(Scanner scanner) {
        System.out.println("게임에 참여할 사람들의 이름을 입력하세요.(쉼표 기준으로 분리)");

        String string = scanner.nextLine();
        String[] strings = string.split(",");

        return strings;
    }
}
