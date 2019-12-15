package view;

import java.util.ArrayList;
import java.util.List;

import domain.Rule;
import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;

public class OutputView {
    private static final String[] FIRST_DISTRIBUTION_MESSAGES = {"딜러와 ", "에게 2장의 카드를 나누었습니다."};
    private static final String DEALER_STATE_MESSAGE = "딜러: ";
    private static final String USER_CARDS_STATE_MESSAGE = "카드: ";
    private static final String DEALER_DRAW_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String RESULT_SCORE_MESSAGE = " - 결과: ";
    private static final String RESULT_PROFIT_MESSAGE = "\n## 최종 수익\n";

    public static void printFirstDistributionMessage(ArrayList<Player> players) {
        List<String> playerNames = new ArrayList<>();
        for (Player player : players) {
            playerNames.add(player.getName());
        }
        System.out.println(FIRST_DISTRIBUTION_MESSAGES[0] + String.join(",", playerNames)
            + FIRST_DISTRIBUTION_MESSAGES[1]);
    }

    public static void printCardsState(Dealer dealer, ArrayList<Player> players) {
        System.out.println(DEALER_STATE_MESSAGE + dealer.showFirstCard().toString());
        System.out.println(getPlayersCardsState(players));
    }

    private static String getPlayersCardsState(ArrayList<Player> players) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Player player : players) {
            getCardStateLine(stringBuilder, player);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public static void printPlayerCardState(Player player) {
        StringBuilder stringBuilder = new StringBuilder();
        getCardStateLine(stringBuilder, player);
        stringBuilder.append("\n");
        System.out.println(stringBuilder.toString());
    }

    private static void getCardStateLine(StringBuilder stringBuilder, User user) {
        stringBuilder.append(user.getName());
        stringBuilder.append(USER_CARDS_STATE_MESSAGE);
        stringBuilder.append(cardState(user));
    }

    private static String cardState(User user) {
        ArrayList<String> userCardState = new ArrayList<>();
        for (Card card : user.showCards()) {
            userCardState.add(card.toString());
        }
        return String.join(", ", userCardState);
    }

    public static void printDealerDrawMessage() {
        System.out.println(DEALER_DRAW_MESSAGE);
    }

    public static void printResultState(Dealer dealer, ArrayList<Player> players) {
        StringBuilder stringBuilder = new StringBuilder();
        getCardStateLine(stringBuilder, dealer);
        appendTotalScoreLine(stringBuilder, dealer);
        for (Player player : players) {
            getCardStateLine(stringBuilder, player);
            appendTotalScoreLine(stringBuilder, player);
        }
        System.out.println(stringBuilder.toString());
    }

    private static void appendTotalScoreLine(StringBuilder stringBuilder, User user) {
        stringBuilder.append(RESULT_SCORE_MESSAGE);
        stringBuilder.append(user.getScore());
        stringBuilder.append("\n");
    }

    public static void printResultProfit(Dealer dealer, ArrayList<Player> players) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(RESULT_PROFIT_MESSAGE);
        stringBuilder.append(DEALER_STATE_MESSAGE);
        stringBuilder.append(Rule.getDealerProfit(dealer, players));
        stringBuilder.append("\n");
        for(Player player : players) {
            stringBuilder.append(player.getName());
            stringBuilder.append(": ");
            stringBuilder.append(Rule.getPlayerProfit(dealer, player));
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder.toString());
    }
}
