package game;

import java.util.ArrayList;
import java.util.List;

import domain.distribution.CardDistributionMachine;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import domain.user.User;
import view.InputView;
import view.OutputView;

public class Blackjack {
    private final CardDistributionMachine cardDistributionMachine;
    private final Players players;
    private final Dealer dealer;

    public Blackjack(CardDistributionMachine cardDistributionMachine, Players players, Dealer dealer) {
        this.cardDistributionMachine = cardDistributionMachine;
        this. players = players;
        this.dealer = dealer;
    }

    public void startGame() {
        distributeTwoCard();
        OutputView.printDistributeTwoCard(players, dealer);
        OutputView.printBlackjackUser(getBlackjackUser());
        if (dealer.isBlackjack()) {
            showResultAndProfit();
            return;
        }
        hitUsers();
        showResultAndProfit();
    }

    private void distributeTwoCard() {
        for (int count = 0; count < 2; count++) {
            dealer.addCard(cardDistributionMachine.getCard());
            players.addCardAllPlayer(cardDistributionMachine.getCardsBySize(players.size()));
        }
    }

    private List<User> getBlackjackUser() {
        List<User> users = new ArrayList<>();
        users.addAll(players.findByBlackjack());
        if (dealer.isBlackjack()) {
            users.add(dealer);
        }
        return users;
    }

    private void showResultAndProfit() {
        OutputView.printResult(players, dealer);
        Profits profits = new ProfitGenerator(players, dealer).create();
        OutputView.printProfits(profits, players);
    }

    private void hitUsers() {
        hitPlayers();
        hitDealer();
    }

    private void hitPlayers() {
        for (int index = 0; index < players.size(); index++) {
            hitPlayer(players.getPlayer(index));
        }
    }

    private void hitPlayer(Player player) {
        if (!player.isBlackjack()) {
            hit(player);
        }
    }

    private void hit(Player player) {
        if (!player.isContinueHit(InputView.inputCheckHit(player))) {
            return;
        }
        distributeCard(player);
        if (player.isBust()) {
            OutputView.printBustAndCard(player);
            return;
        }
        OutputView.printCard(player);
        hit(player);
    }

    private void hitDealer() {
        if (!dealer.isHaveToHit()) {
            return;
        }
        OutputView.printDealerHit();
        distributeCard(dealer);
        if (dealer.isBust()) {
            OutputView.printBustAndCard(dealer);
            return;
        }
        hitDealer();
    }

    private void distributeCard(User user) {
        user.addCard(cardDistributionMachine.getCard());
    }
}