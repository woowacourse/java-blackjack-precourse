import Controller.BlackjackGame;
import Controller.UsersFactory;
import View.InputController;
import domain.card.Deck;
import domain.user.User;

import java.util.List;

public class BlackjackApplication {
    public static void main(String[] args) {
        String playerNames = InputController.askPlayerNames();
        List<User> users = UsersFactory.createUsers(playerNames);
        Deck deck = new Deck();

        BlackjackGame blackjackGame = new BlackjackGame(users, deck);
        blackjackGame.play();
    }
}