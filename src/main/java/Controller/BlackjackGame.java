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
    public static final int DEALER_MINIMUM_SCORE_TO_PASS = 17;
    public static final String DEALER_SCORE_IS_UNDER_MINIMUM_MESSAGE = "딜러의 점수가 17미만이므로 1장의 카드를 더 받습니다.";

    private List<User> users;
    private Deck deck;

    public BlackjackGame(List<User> users, Deck deck) {
        this.users = users;
        this.deck = deck;
    }

    public void play() {
        distributeInitialCards();
        OutputView.printCardsOfAllUsers(users);

        playTurnsForAllUsers();
    }

    private void distributeInitialCards() {
        try {
            for (User user : users) {
                user.addCard(deck.draw());
                user.addCard(deck.draw());
            }
        } catch (DeckHasNoCardException e) {

        }
    }

    private void playTurnsForAllUsers() {
        for (User user : users) {
            playTurnForOneUser(user);
        }
    }

    private void playTurnForOneUser(User user) {
        if(user instanceof Player) {
            playTurnForOnePlayer((Player)user);
            return;
        }
        playTurnForOneDealer((Dealer)user);
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