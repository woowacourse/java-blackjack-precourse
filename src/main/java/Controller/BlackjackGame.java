package Controller;

import View.InputController;
import View.OutputView;
import domain.card.Deck;
import domain.user.User;
import Exception.DeckHasNoCardException;

import java.util.List;

public class BlackjackGame {
    private List<User> users;
    private Deck deck;

    public BlackjackGame(List<User> users, Deck deck) {
        this.users = users;
        this.deck = deck;
    }

    public void play() {
        distributeInitialCards();
        OutputView.printUserCards(users);

        playTurns();
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

    private void playTurns() {
        for (User user : users) {
            if(InputController.askIfGetCard(user)) {
                user.addCard(deck.draw());
            }
        }
    }
}
