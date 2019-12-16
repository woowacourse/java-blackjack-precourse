package domain.result;

import domain.user.Player;
import domain.user.User;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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
        List<User> players = new LinkedList<>(users.subList(0, users.size()));
        User dealer = users.get(users.size());

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
                .filter(User::isBlackjack)
                .collect(Collectors.toList());

        handleLosers(bustPlayers, LOSS_ONE);

        if (dealer.isBust()) {
            handleWinners(players, PROFIT_ONE);
            handleDealer(dealer, dealerProfit);
        }

        // 숫자가 큰 사람이 승리하도록 처리

    }

    private void handleWinners(List<User> users, Double profit) {
        users.stream()
                .forEach(player -> gameResult.put(player, ((Player) player).multiplyBettingMoneyBy(profit)));

        users.stream()
                .forEach(player -> players.remove(player));
    }

    private void handleLosers(List<User> users, Double loss) {
        users.stream()
                .forEach(player -> gameResult.put(player, ((Player) player).multiplyBettingMoneyBy(loss)));

        users.stream()
                .forEach(player -> players.remove(player));
    }

    private void handleDealer(User dealer, Double dealerProfit) {
        gameResult.put(dealer, dealerProfit);
    }
}
