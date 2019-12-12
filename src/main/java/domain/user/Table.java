package domain.user;

import java.util.LinkedList;
import java.util.List;

public class Table {
    private static final int DRAW = 0;
    private static final int DEALER_INDEX = 0;
    private static final int BLACK_JACK_NUMBER = 10;
    private List<Player> players = new LinkedList<>();
    private double tableMoney = 0;

    public Table() {
        players.add(new Dealer("딜러", 0));
    }

    public void addMember(Player player) {
        players.add(player);
    }

    public List<Player> getTable() {
        return players;
    }

    public List<Double> calculateMoney() {
        List<Double> balances = new LinkedList<>();
        int dealerScore = players.get(DEALER_INDEX).calculateScore(BLACK_JACK_NUMBER);
        balances.add(tableMoney);

        for (int i = 1; i < players.size(); i++) {
            balances.add(getMoneyPlayer(i, dealerScore));
        }
        balances.set(DEALER_INDEX, -getMoneyDealer(balances));

        return balances;
    }

    private double getMoneyPlayer(int index, int dealerScore) {
        if (players.get(index).calculateScore(BLACK_JACK_NUMBER) > dealerScore) {
            tableMoney -= players.get(index).getBettingMoney();
            return players.get(index).getBettingMoney();
        }
        if (players.get(index).calculateScore(BLACK_JACK_NUMBER) < dealerScore) {
            tableMoney += players.get(index).getBettingMoney();
            return -players.get(index).getBettingMoney();
        }

        return DRAW;
    }

    private Double getMoneyDealer(List<Double> playerMoney) {
        return playerMoney.stream().reduce(Double::sum).orElseThrow(IllegalStateException::new);
    }
}
