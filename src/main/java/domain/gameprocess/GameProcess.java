package domain.gameprocess;

import domain.ui.UserInterfaceMachine;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameProcess {
    private final UserInterfaceMachine userInterfaceMachine= new UserInterfaceMachine();

    public void play() {
        Dealer dealer = createDealer();
        List<Player> players = createPlayers();

        handOutTwoCardsToAllUsers(dealer, players);

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
        ArrayList<Player> players = new ArrayList<Player>();
        String[] playerNames = userInterfaceMachine.scanNames();

        for (String playerName : playerNames) {
            players.add(new Player(playerName,
                userInterfaceMachine.scanBetAmountOfPlayer(playerName)));
        }

        return players;
    }

    private void handOutTwoCardsToAllUsers(Dealer dealer, List<Player> users) {

    }

    private void handOutTwoCardsToUser(User user) {

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
