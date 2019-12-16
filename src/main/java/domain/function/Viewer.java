package domain.function;

import domain.user.BlackjackUser;
import domain.user.Dealer;
import domain.user.Player;

import java.util.List;

public class Viewer {

    public static void printDistributedCards(Dealer dealer, List<String> playerNameList, List<Player> playerList) {
        String playerName = String.join(",", playerNameList);
        System.out.println(String.format("딜러와 %s에게 각각 2장의 카드를 나누어 주었습니다.", playerName));
        Viewer.printFirstCardOnly(dealer);
        for (Player player : playerList) {
            Viewer.printAllCards(player);
            System.out.println();
        }
        System.out.println();
    }

    public static void printFirstCardOnly(Dealer dealer) {
        System.out.println(String.format("딜러 카드: %s", dealer.getFirstCardName()));
    }

    public static void printAllCards(BlackjackUser blackjackUser) {
        String cardNames = blackjackUser.getAllCardNames();
        System.out.print(String.format("%s 카드: %s", blackjackUser.getName(), cardNames));
    }

    public static void printTotalScore(BlackjackUser blackjackUser) {
        printAllCards(blackjackUser);
        int totalScore = blackjackUser.getTotalScore();
        System.out.println(String.format(" - 결과: %d", totalScore));
    }

    public static void printGameParticipantTotalScore(Dealer dealer, List<Player> playerList) {
        printTotalScore(dealer);
        for (Player player : playerList) {
            printTotalScore(player);
        }
        System.out.println();
    }

    public static void printGameParticipantProfit(Dealer dealer, List<Player> playerList) {
        System.out.println("## 최종수익 ##");
        List<Integer> playerProfitList = ProfitCalculator.getPlayerProfitList(dealer, playerList);
        int dealerProfit = ProfitCalculator.getDealerProfitSum(playerProfitList);
        System.out.println("딜러: " + dealerProfit);
        for (int i = 0; i < playerList.size(); i++) {
            System.out.println(playerList.get(i).getName() + ": " + playerProfitList.get(i));
        }
    }
}
