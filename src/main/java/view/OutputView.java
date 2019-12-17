package view;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import domain.user.User;
import game.Profits;

public class OutputView {
    private static StringBuilder builder;
    private static final String NEW_LINE = "\n";
    private static final String COLON = ": ";
    private static final String COMMA = ", ";
    private static final String DEALER = "딜러: ";
    private static final String DEALER_AND = "딜러와 ";
    private static final String DEALER_HIT = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String CARD_COLON = "카드: ";
    private static final String DISTRIBUTE_TWO_CARD = "에게 2장의 카드를 나누었습니다.";
    private static final String BLACK_JACK = " 블랙잭!";
    private static final String RESULT_COLON = " - 결과";
    private static final String USER_BUST = "님은 21을 초과하였습니다!";
    private static final String FINAL_PROFIT = "### 최종수익";

    public static void printDistributeTwoCard(Players players, Dealer dealer) {
        builder = new StringBuilder(NEW_LINE);
        builder.append(DEALER_AND + makePlayersNameString(players) + DISTRIBUTE_TWO_CARD + NEW_LINE);
        builder.append(DEALER + dealer.getCardByIndex(0).getSymbolName()
                + dealer.getCardByIndex(0).getTypeName() + NEW_LINE);
        for (int index = 0; index < players.size(); index++) {
            Player player = players.getPlayer(index);
            builder.append(makeUserCardString(player) + NEW_LINE);
        }
        println(builder.toString());
    }

    private static String makePlayersNameString(Players players) {
        List<String> playerNames = new ArrayList<>();
        for (int index = 0; index < players.size(); index++) {
            playerNames.add(players.getPlayer(index).getName());
        }
        return String.join(COMMA, playerNames);
    }

    private static String makeUserCardString(User user) {
        List<String> userCards = new ArrayList<>();
        for (int index = 0; index < user.getCardsSize(); index++) {
            Card card = user.getCardByIndex(index);
            userCards.add(card.getSymbolName() + card.getTypeName());
        }
        return user.getName() + CARD_COLON + String.join(COMMA, userCards);
    }

    public static void printBlackjackUser(List<User> users) {
        if (users.isEmpty()) {
            return;
        }
        builder = new StringBuilder();
        for (User user : users) {
            builder.append(user.getName() + BLACK_JACK + NEW_LINE);
        }
        println(builder.toString());
    }

    public static void printBustAndCard(User user) {
        println(user.getName() + USER_BUST);
        printCard(user);
    }

    public static void printCard(User user) {
        println(makeUserCardString(user) + NEW_LINE);
    }

    public static void printDealerHit() {
        println(DEALER_HIT + NEW_LINE);
    }

    public static void printResult(Players players, Dealer dealer) {
        builder = new StringBuilder();
        builder.append(makeUserResult(dealer));
        for (int index = 0; index < players.size(); index++) {
            Player player = players.getPlayer(index);
            builder.append(makeUserResult(player));
        }
        println(builder.toString());
    }

    private static String makeUserResult(User user) {
        return makeUserCardString(user) + RESULT_COLON + user.getTotalScore() + NEW_LINE;
    }

    public static void printProfits(Profits profits, Players players) {
        builder = new StringBuilder();
        builder.append(FINAL_PROFIT + NEW_LINE);
        builder.append(DEALER + (int) profits.getDealerProfit() + NEW_LINE);
        for (int index = 0; index < players.size(); index++) {
            String name = players.getPlayer(index).getName();
            builder.append(name + COLON + (int) profits.getPlayerProfit(name) + NEW_LINE);
        }
        println(builder.toString());
    }

    public static void println(String text) {
        System.out.println(text);
    }
}
