import Controller.BlackjackGame;
import Controller.UsersFactory;
import View.InputView;
import domain.card.Deck;
import domain.user.User;

import java.util.List;

public class BlackjackApplication {
    public static void main(String[] args) {
        String playerNames = InputView.inputPlayerNames();
        List<User> users = UsersFactory.createUsers(playerNames);
        Deck deck = new Deck();

        BlackjackGame.play(users, deck);
    }
}