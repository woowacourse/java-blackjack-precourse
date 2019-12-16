package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Gamer;
import domain.user.Player;
import view.InputView;
import view.OutputView;

public class GameModel {
    private static final int SECOND = 2;
    private static final double FIRST_BLACKJACK_PROFIT = 0.5;
    private static final Integer TWENTY = 20;
    private static final double WIN_RATE = 1;
    private static final double DRAW_RATE = 0;
    private static final double LOSE_RATE = -1;
    private final List<Player> players = new ArrayList<>();
    private final Dealer dealer = new Dealer();
    private static final Deck deck = Deck.getInstance();

    public GameModel(List<String> userNames, List<Double> userBettingMoneys) {
        for (int i = 0; i < userNames.size(); i++) {
            players.add(new Player(userNames.get(i), userBettingMoneys.get(i)));
        }
    }

    public void play() throws IOException {
        giveTwoCardsToEveryOne();
        checkPlayerFirstBlackJack();
        giveOneMoreCardToPlayers();
        checkOneMoreCardToDealer();
        showResultOfEveryCards();
        for (Player player : players) {
            setResult(player,dealer);
        }
        setResultOfDealer();
    }

    private void setResultOfDealer() {
        OutputView.printDealer(dealer.getProfit(players));
    }

    private boolean setResult(Player player, Dealer dealer) {
        if(player.isBlackJack() && !dealer.isBlackJack())
            OutputView.printPlayerProfit(player);
        if (player.winGame(dealer) && !player.isBlackJack()) {
            player.multiplyProfit(WIN_RATE);
            OutputView.printPlayerProfit(player);
            return true;
        }
        if (player.drawGame(dealer) && !player.isBurst() && !dealer.isBurst()) {
            player.multiplyProfit(DRAW_RATE);
            OutputView.printPlayerProfit(player);
            return true;
        }
        if(!player.isBlackJack()){
            player.multiplyProfit(LOSE_RATE);
            OutputView.printPlayerProfit(player);
        }
        return false;
    }

    private void showResultOfEveryCards() {
        for (Player player : players) {
            OutputView.printPlayerCardsAndResult(player);
        }
        OutputView.printDealerCardsAndResult(dealer);
    }

    private void checkOneMoreCardToDealer() {
        if (dealer.shouldHaveOneMoreCard()) {
            dealer.addCard(deck.getRandomCard());
            OutputView.printDealerGetOneMore();
        }
    }

    private void giveOneMoreCardToPlayers() throws IOException {
        for (Player player : players) {
            checkCanHaveMoreAndGive(player);
        }
    }

    private boolean checkCanHaveMoreAndGive(Player player) throws IOException {
        if (canHaveMore(player) && wantOneMore(player)) {
            player.addCard(deck.getRandomCard());
            OutputView.printCards(player);
            return checkCanHaveMoreAndGive(player);
        }
        return false;
    }

    private boolean canHaveMore(Player player) {
        return player.sumOfCard() <= TWENTY;
    }

    private boolean wantOneMore(Player player) throws IOException {
        InputView inputView = new InputView();
        return inputView.wantOneMore(player);
    }

    private void checkPlayerFirstBlackJack() {
        for (Player player : players) {
            checkFirstBlackjack(player);
        }
    }

    private void checkFirstBlackjack(Player player) {
        if (player.isBlackJack() && !dealer.isBlackJack())
            player.multiplyProfit(FIRST_BLACKJACK_PROFIT);
    }

    private void giveTwoCardsToEveryOne() {
        giveTwoCardsToPlayer();
        giveTwoCardsToDealer();
    }

    private void giveTwoCardsToPlayer() {
        for (Player player : players) {
            giveEachTwoCards(player);
            OutputView.printCards(player);
        }
    }

    private void giveTwoCardsToDealer() {
        giveEachTwoCards(dealer);
        OutputView.printDealerOneCard(dealer);
    }

    private void giveEachTwoCards(Gamer gamer) {
        for (int i = 0; i < SECOND; i++) {
            gamer.addCard(deck.getRandomCard());
        }
    }

}
