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
    private static final double FIRST_BLACKJACK_PROFIT = 1.5;
    private static final Integer TWENTY = 20;
    private final List<Player> players = new ArrayList<>();
    private final Dealer dealer = new Dealer();
    private static final Deck deck = Deck.getInstance();
    private int totalBettingMoney = 0;

    public GameModel(List<String> userNames, List<Double> userBettingMoneys) {
        for (int i = 0; i < userNames.size(); i++) {
            players.add(new Player(userNames.get(i), userBettingMoneys.get(i)));
            totalBettingMoney += userBettingMoneys.get(i);
        }
    }

    public void play() throws IOException {
        giveTwoCardsToEveryOne();
        checkPlayerFirstBlackJack();
        giveOneMoreCardToPlayers();
    }

    private void giveOneMoreCardToPlayers() throws IOException {
        for(Player player : players){
            checkCanHaveMoreAndGive(player);
        }
    }

    private void checkCanHaveMoreAndGive(Player player) throws IOException {
        if(wantOneMore(player) && canHaveMore(player)){
            player.addCard(deck.getRandomCard());
        }
        OutputView.printCards(player);
    }

    private boolean canHaveMore(Player player) {
        return player.sumOfCard() <= TWENTY;
    }

    private boolean wantOneMore(Player player) throws IOException {
        InputView inputView = new InputView();
        return inputView.wantOneMore(player);
    }

    private void checkPlayerFirstBlackJack() {
        for(Player player : players){
            checkFirstBlackjack(player);
        }
    }

    private void checkFirstBlackjack(Player player) {
        if(player.isBlackJack() && !dealer.isBlackJack())
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
