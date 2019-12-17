package domain.result;

import domain.user.Player;
import domain.user.User;

import java.util.*;
import java.util.stream.Collectors;

public class GameResult {
    private static final Double PROFIT_ZERO = 0.0;
    private static final Double PROFIT_ONE_POINT_FIVE = 1.5;
    private static final Double PROFIT_ONE = 1.0;
    private static final Double LOSS_ONE = 1.0;
    private static final Double MINUS = -1.0;

    private HashMap<User, Double> gameResult;
    private List<User> players;
    private User dealer;
    private Double dealerProfit;

    private GameResult(List<User> players, User dealer) {
        this.gameResult = new HashMap<>();
        this.players = players;
        this.dealer = dealer;
        this.dealerProfit = 0.0;
    }

    public static GameResult create(List<User> users) {
        List<User> players = new LinkedList<>(users.subList(0, users.size() - 1));
        User dealer = users.get(users.size() - 1);

        return new GameResult(players, dealer);
    }

    public boolean hasBlackjack() {
        List<User> blackjackPlayers = getBlackjackPlayers();

        if (blackjackPlayers.isEmpty()) {
            return false;
        }

        if (dealer.isBlackjack()) {
            handleUsersWhenDealerIsBlackjack(blackjackPlayers);
            return true;
        }

        handleUsersWhenPlayersAreBlackjack(blackjackPlayers);
        return true;
    }

    private List<User> getBlackjackPlayers() {
        return players.stream()
                .filter(User::isBlackjack)
                .collect(Collectors.toList());
    }

    private void handleUsersWhenPlayersAreBlackjack(List<User> blackjackPlayers) {
        handleWinners(blackjackPlayers, PROFIT_ONE_POINT_FIVE);
        handleLosers(players, LOSS_ONE);
        handleDealer(dealer, dealerProfit);
    }

    private void handleUsersWhenDealerIsBlackjack(List<User> blackjackPlayers) {
        handleWinners(blackjackPlayers, PROFIT_ZERO);
        handleLosers(players, LOSS_ONE);
        handleDealer(dealer, dealerProfit);
    }

    public void decideGameResult() {
        List<User> bustPlayers = getBustPlayers();

        handleLosers(bustPlayers, LOSS_ONE);

        if (players.isEmpty()) {
            handleDealer(dealer, dealerProfit);
        }

        if (dealer.isBust()) {
            handleUsersWhenDealerIsBust();
        }

//        System.out.println("아직 미구현");
//        System.out.println("size : " + gameResult.size());

    }

    private List<User> getBustPlayers() {
        return players.stream()
                .filter(User::isBust)
                .collect(Collectors.toList());
    }

    private void handleUsersWhenDealerIsBust() {
        handleWinners(players, PROFIT_ONE);
        handleDealer(dealer, dealerProfit);
    }

    public void isInvalid() {
        handleWinners(players, PROFIT_ZERO);
        handleDealer(dealer, dealerProfit);
    }

    private void handleWinners(List<User> winners, Double profit) {
        winners.stream()
                .forEach(winner -> gameResult.put(winner, ((Player) winner).multiplyBettingMoneyBy(profit)));

        winners.stream()
                .mapToDouble(winner -> ((Player) winner).multiplyBettingMoneyBy(profit))
                .forEach(winnersProfit -> dealerProfit -= winnersProfit);

        players.removeAll(winners);
    }

    private void handleLosers(List<User> losers, Double loss) {
        losers.stream()
                .forEach(loser -> gameResult.put(loser, MINUS * ((Player) loser).multiplyBettingMoneyBy(loss)));

        losers.stream()
                .mapToDouble(loser -> ((Player) loser).multiplyBettingMoneyBy(loss))
                .forEach(losersLoss -> dealerProfit += losersLoss);

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
