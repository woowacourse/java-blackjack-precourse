package domain.result;

import domain.user.Player;
import domain.user.User;

import java.util.*;
import java.util.stream.Collectors;

public class GameResult {
    private static final Double PROFIT_ZERO = 0.0;
    private static final Double PROFIT_ONE_POINT_FIVE = 1.5;
    private static final Double PROFIT_ONE = 1.0;
    private static final Double LOSS_ONE = -1.0;

    private HashMap<User, Double> gameResult;
    private List<User> players;
    private User dealer;
    private Double dealerProfit;

    public GameResult(List<User> players, User dealer) {
        this.gameResult = new HashMap<>();
        this.players = players;
        this.dealer = dealer;
        this.dealerProfit = 0.0;
    }

    public static GameResult create(List<User> users) {
        List<User> players = new LinkedList<>(users.subList(0, users.size() - 1));
        System.out.println("players >> ");
        for (User user : players) {
            System.out.println("player : " + user.getName());
        }
        System.out.println("Dealer >> ");
        User dealer = users.get(users.size() - 1);
        System.out.println("dealer : " + dealer.getName());


        return new GameResult(players, dealer);
    }

    public boolean hasBlackjack() {
        List<User> blackjackPlayers = players.stream()
                .filter(User::isBlackjack)
                .collect(Collectors.toList());

        if (blackjackPlayers.isEmpty()) {
            return false;
        }

        if (dealer.isBlackjack()) {
            handleWinners(blackjackPlayers, PROFIT_ZERO);
            handleLosers(players, LOSS_ONE);
            handleDealer(dealer, dealerProfit);
            return true;
        }

        handleWinners(blackjackPlayers, PROFIT_ONE_POINT_FIVE);
        handleLosers(players, LOSS_ONE);
        handleDealer(dealer, dealerProfit);
        return true;
    }

    public void decideGameResult() {
        List<User> bustPlayers = players.stream()
                .filter(User::isBust)
                .collect(Collectors.toList());

        handleLosers(bustPlayers, LOSS_ONE);

        if (dealer.isBust()) {
            handleWinners(players, PROFIT_ONE);
            handleDealer(dealer, dealerProfit);
        }

//        System.out.println("아직 미구현");
//        System.out.println("size : " + gameResult.size());

    }

    private void handleWinners(List<User> winners, Double profit) {
        winners.stream()
                .forEach(winner -> gameResult.put(winner, ((Player) winner).multiplyBettingMoneyBy(profit)));

        players.removeAll(winners);
    }

    private void handleLosers(List<User> losers, Double loss) {
        losers.stream()
                .forEach(loser -> gameResult.put(loser, ((Player) loser).multiplyBettingMoneyBy(loss)));

        players.removeAll(losers);
    }

    private void handleDealer(User dealer, Double dealerProfit) {
        gameResult.put(dealer, dealerProfit);

        players.remove(dealer);
    }

    public Map<User, Double> getGameResult() {
        return Collections.unmodifiableMap(gameResult);
    }
}
