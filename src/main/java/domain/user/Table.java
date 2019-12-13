package domain.user;

import domain.manager.Manual;

import java.util.LinkedList;
import java.util.List;

public class Table {
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

        balances.add(tableMoney);
        for (int i = Manual.PLAYER_INIT.getValue(); i < players.size(); i++) {
            balances.add(getMoneyPlayer(i, players.get(Manual.DEALER_POSITION.getValue()).calculateScore()));
        }
        balances.set(Manual.DEALER_POSITION.getValue(), -getMoneyDealer(balances));

        return balances;
    }

    private double getMoneyPlayer(int index, int dealerScore) {
        if (players.get(index).calculateScore() > dealerScore
                || (dealerScore > Manual.BURST.getValue()
                && players.get(index).calculateScore()
                < Manual.BURST.getValue())) {
            tableMoney -= players.get(index).getBettingMoney();
            return players.get(index).getBettingMoney();
        }
        return loseMoneyPlayer(index, dealerScore);
    }

    private double loseMoneyPlayer(int index, int dealerScore) {
        if (players.get(index).calculateScore() < dealerScore
                || players.get(index).calculateScore()
                > Manual.BURST.getValue()) {
            tableMoney += players.get(index).getBettingMoney();
            return -players.get(index).getBettingMoney();
        }
        return Manual.EMPTY.getValue();
    }

    private Double getMoneyDealer(List<Double> playerMoney) {
        return playerMoney.stream().reduce(Double::sum).orElseThrow(IllegalStateException::new);
    }
}
