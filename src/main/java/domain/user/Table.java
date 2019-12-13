package domain.user;

import domain.manager.Manager;
import domain.manager.Manual;

import java.util.LinkedList;
import java.util.List;

public class Table {
    private static final double BONUS = 1.5;
    private List<Player> players = new LinkedList<>();
    private List<Boolean> blackjack = new LinkedList<>();

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

        balances.add((double) Manual.EMPTY.getValue());
        for (int i = Manual.PLAYER_INIT.getValue(); i < players.size(); i++) {
            balances.add(getMoneyPlayer(i, players.get(
                    Manual.DEALER_POSITION.getValue()).calculateScore()));
        }
        balances.set(Manual.DEALER_POSITION.getValue(), -getMoneyDealer(balances));
        for (int i = 0; i < players.size(); i++) {
            blackjackBonus(balances, i);
        }
        return balances;
    }

    private void blackjackBonus(List<Double> balances, int index) {
        if (index != Manual.DEALER_POSITION.getValue()
                && blackjack.get(index)
                && !blackjack.get(Manual.DEALER_POSITION.getValue())) {
            balances.set(Manual.DEALER_POSITION.getValue(),
                    balances.get(Manual.DEALER_POSITION.getValue())
                            + balances.get(index));
            balances.set(index, balances.get(index) * BONUS);
            balances.set(Manual.DEALER_POSITION.getValue(),
                    balances.get(Manual.DEALER_POSITION.getValue())
                            - balances.get(index));
        }
    }

    private double getMoneyPlayer(int index, int dealerScore) {
        double bonusBalance = calculateBlackjack(index);

        if (bonusBalance != Manual.EMPTY.getValue()) {
            return bonusBalance;
        }
        if ((players.get(index).calculateScore() < dealerScore
                && dealerScore <= Manual.BURST.getValue())
                || players.get(index).calculateScore()
                > Manual.BURST.getValue()) {
            return -players.get(index).getBettingMoney();
        }
        return winMoneyPlayer(index, dealerScore);
    }

    private double winMoneyPlayer(int index, int dealerScore) {
        if (players.get(index).calculateScore() > dealerScore
                || (dealerScore > Manual.BURST.getValue()
                && players.get(index).calculateScore()
                <= Manual.BURST.getValue())) {
            return players.get(index).getBettingMoney();
        }
        return Manual.DEFAULT.getValue();
    }

    private Double getMoneyDealer(List<Double> playerMoney) {
        return playerMoney.stream().reduce(Double::sum).orElseThrow(IllegalStateException::new);
    }

    public void iteratePlayer() {
        for (Player player : players) {
            hasBlackjackPlayer(player);
        }
    }

    private void hasBlackjackPlayer(Player player) {
        if (player.calculateScore()
                == Manual.BLACKJACK.getValue() && player.getCards().size()
                == Manual.Pair.getValue()) {
            blackjack.add(true);
        }
        if (player.calculateScore()
                != Manual.BLACKJACK.getValue() || player.getCards().size()
                != Manual.Pair.getValue()) {
            blackjack.add(false);
        }
    }

    private double calculateBlackjack(int index) {
        return blackjackWin(index);
    }

    private double blackjackWin(int index) {
        if ((players.get(index).calculateScore() == Manual.BLACKJACK.getValue()
                && players.get(
                        Manual.DEALER_POSITION.getValue())
                .calculateScore() == Manual.BLACKJACK.getValue())
                && (blackjack.get(index)
                && !blackjack.get(Manual.DEALER_POSITION.getValue()))) {
            return players.get(index).getBettingMoney();
        }
        return blackjackDealerWin(index);
    }

    private double blackjackDealerWin(int index) {
        if ((players.get(index).calculateScore() == Manual.BLACKJACK.getValue()
                && players.get(
                Manual.DEALER_POSITION.getValue())
                .calculateScore() == Manual.BLACKJACK.getValue())
                && (!blackjack.get(index)
                && blackjack.get(Manual.DEALER_POSITION.getValue()))) {
            return -players.get(index).getBettingMoney();
        }
        return Manual.EMPTY.getValue();
    }

    public void setBlackjack(List<Boolean> blackjack) {
        this.blackjack = blackjack;
    }
}
