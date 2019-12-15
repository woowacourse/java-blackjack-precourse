package domain.gameprocess;

import domain.ui.UserInterfaceMachine;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
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
        return null;
    }

    private List<Player> createPlayers() {
        return null;
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
