import Controller.UsersFactory;
import View.InputView;
import domain.card.Card;
import domain.card.CardFactory;
import domain.user.User;

import java.util.List;

public class BlackjackApplication {
    public static void main(String[] args) {
        String playerNames = InputView.inputPlayerNames();
        List<User> users = UsersFactory.createUsers(playerNames);
        List<Card> deckOfCards = CardFactory.create();
    }
}