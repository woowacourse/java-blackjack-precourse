package Controller;

import View.InputController;
import View.OutputView;
import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import Exception.DeckHasNoCardException;

import java.util.List;

public class BlackjackGame {
    private static final int INITIAL_NUMBER_OF_CARDS = 2;
    private static final int DEALER_MINIMUM_SCORE_TO_PASS = 17;
    private static final String DEALER_SCORE_IS_UNDER_MINIMUM_MESSAGE = "딜러의 점수가 17미만이므로 1장의 카드를 더 받습니다.";

    private List<User> users;
    private Deck deck;

    public BlackjackGame(List<User> users, Deck deck) {
        this.users = users;
        this.deck = deck;
    }

    public void play() {
        try {
            distributeInitialCardsToAllUsers();
            playTurnsForAllUsers();

        } catch (DeckHasNoCardException e) {

        }
    }

    private void distributeInitialCardsToAllUsers() {
        for (User user : users) {
            distributeInitialCardsToOneUser(user);
        }
    }

    private void distributeInitialCardsToOneUser(User user) {
        if (user instanceof Player) {
            distributeInitialCardsToOnePlayer((Player) user);
            return;
        }
        distributeInitialCardsToOneDealer((Dealer) user);
    }

    private void distributeInitialCardsToOnePlayer(Player player) {
        for (int i = 0; i < INITIAL_NUMBER_OF_CARDS; i++) {
            player.addCard(deck.draw());
        }
        OutputView.printCardsOfOneUser(player);
    }

    private void distributeInitialCardsToOneDealer(Dealer dealer) {
        for (int i = 0; i < INITIAL_NUMBER_OF_CARDS; i++) {
            dealer.addCard(deck.draw());
        }
        OutputView.printCardsOfOneDealerExceptOneCard(dealer);
    }

    private void playTurnsForAllUsers() {
        for (User user : users) {
            playTurnForOneUser(user);
        }
    }

    private void playTurnForOneUser(User user) {
        if (user instanceof Player) {
            playTurnForOnePlayer((Player) user);
            return;
        }
        playTurnForOneDealer((Dealer) user);
    }

    private void playTurnForOnePlayer(Player player) {
        while (InputController.askIfGetCard(player)) {
            player.addCard(deck.draw());
            OutputView.printCardsOfOneUser(player);
        }
        OutputView.printCardsOfOneUser(player);
    }

    private void playTurnForOneDealer(Dealer dealer) {
        while (dealer.getScoreOfCards() < DEALER_MINIMUM_SCORE_TO_PASS) {
            System.out.println(DEALER_SCORE_IS_UNDER_MINIMUM_MESSAGE);
            dealer.addCard(deck.draw());
        }
        OutputView.printCardsOfOneUser(dealer);
    }
}