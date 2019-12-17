package view;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public static void printInitDistributionMessage(List<Player> playerList) {
        System.out.println("\n[카드 현황]");
        StringBuilder sb = new StringBuilder();
        List<String> playerNameList = playerList.stream()
                .map(p -> p.getName())
                .collect(Collectors.toList());

        sb.append("딜러와 ");
        sb.append(String.join(", ", playerNameList));
        sb.append("에게 " + playerList.size() + "장의 카드를 나누었습니다.");
        System.out.println(sb);
    }

    public static void printCardStatus(Dealer dealer) {
        System.out.println("딜러 : " + dealer.toString());
    }

    public static void printCardStatus(Player player) {
        System.out.println(player.getName() + " : " + player.toString());
    }

    public static void printDealerGetCardMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printResultStatus(Dealer dealer) {
        System.out.println("\n[최종 결과]");
        printCardStatus(dealer);
        printSumScore(dealer);
    }

    public static void printResultStatus(Player player) {
        printCardStatus(player);
        printSumScore(player);
    }

    private static void printSumScore(User user) {
        System.out.println("결과 : " + user.getSumScore());
    }
}
