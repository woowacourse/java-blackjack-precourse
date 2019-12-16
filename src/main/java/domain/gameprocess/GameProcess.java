package domain.gameprocess;

import domain.card.CardDivider;
import domain.card.CardFactory;
import domain.ui.UserInterfaceMachine;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import java.util.ArrayList;
import java.util.List;

public class GameProcess {
    private final UserInterfaceMachine userInterfaceMachine = new UserInterfaceMachine();
    private final CardDivider cardDivider = new CardDivider(new CardFactory().create());

    public void play() {
        Dealer dealer = createDealer();
        List<Player> players = createPlayers();

        handOutTwoCardsToAllUsers(dealer, players);
        userInterfaceMachine.printFistTwoCardGivenSituation(dealer, players);

        for (Player player : players) {
            handOutCardsUntilPlayerWantStop(player);
        }

        handOutCardsUntilDealerCannotGet(dealer);

        userInterfaceMachine.printUsersCardsResults(dealer, players);
        userInterfaceMachine.printUsersFinalRevenues(dealer, players);
    }

    private Dealer createDealer() {
        return new Dealer();
    }

    private List<Player> createPlayers() {
        List<Player> players = new ArrayList<Player>();
        List<String> playerNames = userInterfaceMachine.scanNames();

        for (String playerName : playerNames) {
            players.add(new Player(playerName,
                userInterfaceMachine.scanBetAmountOfPlayer(playerName)));
        }

        return players;
    }

    private void handOutTwoCardsToAllUsers(Dealer dealer, List<Player> players) {
        handOutTwoCardsToUser(dealer);

        for (Player player : players) {
            handOutTwoCardsToUser(player);
        }


    }

    private void handOutTwoCardsToUser(User user) {
        handOutCardToUser(user);
        handOutCardToUser(user);
    }

    private void handOutCardToUser(User user) {
        user.addCard(cardDivider.getOneRandomCard());
    }

    private void handOutCardsUntilPlayerWantStop(Player player) {

    }

    private boolean doPlayerGetCard(User player, String whetherPlayerReceiveCard) {
        return true;
    }

    private void handOutCardsUntilDealerCannotGet (Dealer dealer) {

    }

    private boolean shouldDealerGetCard(User dealer) {
        return true;
    }
}
