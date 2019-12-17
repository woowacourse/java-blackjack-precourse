package domain.user;

import domain.card.Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Users {
    private static final String PLAYER_NAMES_DELIMITER = ", ";
    private static final String MESSAGE_DISTRIBUTION_HEAD = "딜러와 ";
    private static final String MESSAGE_DISTRIBUTION_TAIL = "에게 2장을 나누었습니다";
    private static final String MESSAGE_LAST_PROFIT = "## 최종수익";
    private static final int INDEX_DEALER = 0;
    private static final int INITIAL_PROFIT_SUM = 0;
    private static final int INITIAL_DISTRIBUTION_NUMBER = 2;
    private List<User> users;

    public Users(List<User> users) {
        this.users = users;
    }

    public void distribute(Stack stack) {
        for (User user : users) {
            addMultipleCards(stack, user, INITIAL_DISTRIBUTION_NUMBER);
        }
        printDistribution();
    }

    private void addMultipleCards(Stack stack, User user, int number) {
        for (int i = 0; i < number; i++) {
            user.addCard(stack.popCard());
        }
    }

    private void printDistribution() {
        ArrayList<String> playerNames = new ArrayList<>();

        for (User user : users) {
            getNameOfPlayer(playerNames, user);
        }
        String playerNameSerial = String.join(PLAYER_NAMES_DELIMITER, playerNames);
        System.out.println(MESSAGE_DISTRIBUTION_HEAD + playerNameSerial + MESSAGE_DISTRIBUTION_TAIL);
        printOpen();
    }

    private void printOpen() {
        List<Player> players = getPlayers();
        Dealer dealer = getDealer();
        for (Player player : players) {
            player.printCards();
        }
        dealer.printCards();
    }

    private void getNameOfPlayer(ArrayList<String> playerNames, User user) {
        if (user instanceof Player) {
            Player player = (Player) user;
            playerNames.add(player.getName());
        }
    }

    public void addDealer() {
        users.add(new Dealer());
    }

    public void processEachUser(Stack stack) {
        List<Player> players = getPlayers();
        Dealer dealer = getDealer();
        for (Player player : players) {
            player.proceed(stack);
        }
        dealer.proceed(stack);
    }

    public void showResult() {
        List<Player> players = getPlayers();
        Dealer dealer = getDealer();
        dealer.showResult();
        for (Player player : players) {
            player.showResult();
        }
    }

    public void calculate() {
        int playersProfitSum = INITIAL_PROFIT_SUM;
        List<Player> players = getPlayers();
        Dealer dealer = getDealer();
        System.out.println(MESSAGE_LAST_PROFIT);
        for (Player player : players) {
            int profit = player.compare(dealer);
            player.printProfit(profit);
            playersProfitSum += profit;
        }
        dealer.printProfit(playersProfitSum);
    }

    private Dealer getDealer() {
        List<Dealer> dealers = users
                .stream()
                .filter(Dealer
                        .class::isInstance)
                .map(Dealer
                        .class::cast)
                .collect(Collectors
                        .toList());

        return dealers.get(INDEX_DEALER);
    }

    private List<Player> getPlayers() {

        return users.stream()
                .filter(Player
                        .class::isInstance)
                .map(Player
                        .class::cast)
                .collect(Collectors
                        .toList());
    }
}
