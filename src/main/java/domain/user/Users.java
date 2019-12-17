package domain.user;

import domain.card.Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Users {
    private List<User> users;

    public Users(List<User> users) {
        this.users = users;
    }

    public void distribute(Stack stack) {
        for (User user : users) {
            addMultipleCards(stack, user, 2);
        }
        printDistribution();
    }

    private void printDistribution() {
        ArrayList<String> playerNames = new ArrayList<>();

        for (User user : users) {
            getNameOfPlayer(playerNames, user);
        }
        String playerNameSerial = String.join(", ", playerNames);
        System.out.println("딜러와 " + playerNameSerial + "에게 2장을 나누었습니다");
        printOpen();
    }

    private void printOpen() {
        for (User user : users) {
            printOpenEachUser(user);
        }
    }

    private void printOpenEachUser(User user) {
        if (user instanceof Player) {
            Player player = (Player) user;
            player.printCards();
        }
        if (user instanceof Dealer) {
            Dealer dealer = (Dealer) user;
            dealer.printCards();
        }
    }

    private void getNameOfPlayer(ArrayList<String> playerNames, User user) {
        if (user instanceof Player) {
            Player player = (Player) user;
            playerNames.add(player.getName());
        }
    }

    private void addMultipleCards(Stack stack, User user, int number) {
        for (int i = 0; i < number; i++) {
            user.addCard(stack.popCard());
        }
    }

    public void addDealer() {
        users.add(new Dealer());
    }

    public void processEachUser(Stack stack) {
        for (User user : users) {
            processPlayer(user, stack);
            processDealer(user, stack);
        }
    }

    private void processDealer(User user, Stack stack) {
        if (user instanceof Dealer) {
            Dealer dealer = (Dealer) user;
            dealer.proceed(stack);
        }
    }

    private void processPlayer(User user, Stack stack) {
        if (user instanceof Player) {
            Player player = (Player) user;
            player.proceed(stack);
        }
    }

    public void showResult() {
        for (User user : users) {
            showDealerResult(user);
            showPlayerResult(user);
        }
    }

    private void showDealerResult(User user) {
        if (user instanceof Dealer) {
            Dealer dealer = (Dealer) user;
            dealer.showResult();
        }
    }

    private void showPlayerResult(User user) {
        if (user instanceof Player) {
            Player player = (Player) user;
            player.showResult();
        }
    }

    private Dealer getDealer() {
        List<Dealer> dealers = users.stream().filter(Dealer.class::isInstance).map(Dealer.class::cast).collect(Collectors.toList());

        return dealers.get(0);
    }

    private List<Player> getPlayers() {

        return users.stream()
                .filter(Player.class::isInstance)
                .map(Player.class::cast)
                .collect(Collectors.toList());
    }

    public void calculate() {
        int playersProfitSum = 0;
        List<Player> players = getPlayers();
        Dealer dealer = getDealer();
        System.out.println("## 최종수익");
        for (Player player : players) {
            int profit = player.compare(dealer);
            player.printProfit(profit);
            playersProfitSum += profit;
        }
        dealer.printProfit(playersProfitSum);
    }
}
