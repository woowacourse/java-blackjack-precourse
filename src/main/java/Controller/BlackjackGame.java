package Controller;

import domain.card.Deck;
import domain.user.User;
import Exception.DeckHasNoCardException;

import java.util.List;

public class BlackjackGame {
    public static void play(List<User> users, Deck deck) throws DeckHasNoCardException {
        try {
            for (User user : users) {
                user.addCard(deck.draw());
                user.addCard(deck.draw());
            }
        } catch (DeckHasNoCardException e) {

        }
    }
}
