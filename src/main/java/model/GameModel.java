package model;

import java.util.ArrayList;
import java.util.List;

import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Gamer;
import domain.user.Player;
import view.OutputView;

public class GameModel {
    private static final int SECOND = 2;
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

    public void play() {
        giveTwoCardsToEveryOne();
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
