package domain;

import domain.function.CardDistributor;
import domain.function.OutcomeRateCalculator;
import domain.user.BlackjackUserResult;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.PlayerInputData;
import static domain.user.BlackjackUserResult.PERFECT_SCORE;
import static domain.user.Dealer.MAX_SCORE_NEEDS_MORE_CARD;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlackJackGame {

    public static void main(String[] args) {
        Dealer dealer = new Dealer();
        List<Player> playerList = PlayerInputData.getPlayerList();
        CardDistributor cardDistributor = new CardDistributor();
        distributeCardsToGameParticipant(dealer, playerList, cardDistributor);
        addCardToParticipant(dealer, playerList, cardDistributor);
        printGameParticipantTotalScore(dealer, playerList);
        printGameParticipantProfit(dealer, playerList);
    }

    private static void distributeCardsToGameParticipant(Dealer dealer, List<Player> playerList, CardDistributor cardDistributor) {
        cardDistributor.giveTwoCardsToBlackjackUser(dealer);
        for (Player player : playerList) {
            cardDistributor.giveTwoCardsToBlackjackUser(player);
        }
        printDistributedCards(dealer, playerList);
    }

    private static void printDistributedCards(Dealer dealer, List<Player> playerList) {
        String playerName = String.join(",", getPlayerNameList(playerList));
        System.out.println(String.format("딜러와 %s에게 각각 2장의 카드를 나누어 주었습니다.", playerName));
        dealer.printFirstCardOnly();
        for (Player player : playerList) {
            player.printAllCards();
            System.out.println();
        }
        System.out.println();
    }

    private static List<String> getPlayerNameList(List<Player> playerList) {
        List<String> playerNameList = new ArrayList<>();
        for (Player player : playerList) {
            playerNameList.add(player.getName());
        }
        return playerNameList;
    }

    private static void addCardToPlayer(List<Player> playerList, CardDistributor cardDistributor) {
        for (Player player : playerList) {
            askCardAddition(player, cardDistributor);
        }
    }

    private static void askCardAddition(Player player, CardDistributor cardDistributor) {
        boolean needToAsk = true;
        while (needToAsk) {
            System.out.println(String.format("%s, 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", player.getName()));
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine();
            needToAsk = needMoreCard(answer, player, cardDistributor) && isUnderPerfectScore(player);
            System.out.println();
        }
        System.out.println();
    }

    private static boolean needMoreCard(String answer, Player player, CardDistributor cardDistributor) {
        if (answer.equals("y")) {
            cardDistributor.giveCardToBlackjackUser(player);
            player.printAllCards();
            return true;
        } else if (answer.equals("n")) {
            player.printAllCards();
            return false;
        }
        System.out.println("※ y와 n만 입력할 수 있습니다.");
        return true;
    }

    private static boolean isUnderPerfectScore(Player player) {
        BlackjackUserResult playerResult = player.createBlackjackUserResult();
        if (playerResult.isPerfectScore()) {
            System.out.println(String.format("\n※ 카드 숫자의 합이 %d이므로 더 이상 카드를 추가하지 않습니다.", PERFECT_SCORE));
            return false;
        }
        if (playerResult.isBust()) {
            System.out.print(String.format("\n※ 카드 숫자의 합이 %d을 넘었으므로 %s는 패배하였습니다.", PERFECT_SCORE, player.getName()));
            return false;
        }
        return true;
    }

    private static void addCardToDealer(Dealer dealer, CardDistributor cardDistributor) {
        if (dealer.doesNeedMoreCard()) {
            cardDistributor.giveCardToBlackjackUser(dealer);
            System.out.println(String.format("딜러는 %d 이하라 한 장의 카드를 더 받았습니다. \n", MAX_SCORE_NEEDS_MORE_CARD));
            return;
        }
        System.out.println(String.format("딜러는 %d 초과로 카드를 더 받지 않습니다. \n", MAX_SCORE_NEEDS_MORE_CARD));
    }

    private static void addCardToParticipant(Dealer dealer, List<Player> playerList, CardDistributor cardDistributor) {
        addCardToPlayer(playerList, cardDistributor);
        addCardToDealer(dealer, cardDistributor);
    }

    private static void printGameParticipantTotalScore(Dealer dealer, List<Player> playerList) {
        dealer.printTotalScore();
        for (Player player : playerList) {
            player.printTotalScore();
        }
        System.out.println();
    }

    private static void printGameParticipantProfit(Dealer dealer, List<Player> playerList) {
        System.out.println("## 최종수익 ##");
        List<Integer> playerProfitList = getPlayerProfitList(dealer, playerList);
        int dealerProfit = getDealerProfitSum(playerProfitList);
        System.out.println("딜러: " + dealerProfit);
        for (int i = 0; i < playerList.size(); i++) {
            System.out.println(playerList.get(i).getName() + ": " + playerProfitList.get(i));
        }
    }

    private static List<Integer> getPlayerProfitList(Dealer dealer, List<Player> playerList) {
        List<Integer> playerProfitList = new ArrayList<>();
        for (Player player : playerList) {
            OutcomeRateCalculator calculator = new OutcomeRateCalculator(dealer.createBlackjackUserResult(), player.createBlackjackUserResult());
            double profitRate = calculator.getPlayerOutcomeRate();
            int profit = player.getProfit(profitRate);
            playerProfitList.add(profit);
        }
        return playerProfitList;
    }

    private static int getDealerProfitSum(List<Integer> playerProfitList) {
        int dealerProfitSum = 0;
        for (int playerProfit : playerProfitList) {
            dealerProfitSum += playerProfit;
        }
        return dealerProfitSum * -1;
    }

}
