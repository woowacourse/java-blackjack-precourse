import View.InputView;
import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.User;
import domain.user.Player;

import java.util.ArrayList;
import java.util.List;

public class BlackjackApplication {
    public static void main(String[] args) {
        String playerNames = InputView.inputPlayerNames();
        List<User> users = createUsers(playerNames);

        List<Card> deckOfCards = CardFactory.create();

    }

    private static List<User> createUsers(String playerNames) {
        List<User> users = new ArrayList<>();
        users.addAll(createPlayersByNames(playerNames));
        users.add(createDealer());
        return users;
    }

    private static List<Player> createPlayersByNames(String playerNames) {
        List<Player> players = new ArrayList<>();

        String[] playerNamesSplit = playerNames.split(",");
        for (String playerName : playerNamesSplit) {
            createPlayerByName(playerName);
        }

        return players;
    }

    private static Player createPlayerByName(String playerName) {
        Double bettingMoney = InputView.inputBettingMoney(playerName);
        return new Player(playerName, bettingMoney);
    }

    private static Dealer createDealer() {
        return new Dealer();
    }
}