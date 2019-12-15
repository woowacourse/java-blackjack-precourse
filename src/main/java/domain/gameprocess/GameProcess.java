package domain.gameprocess;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import java.util.List;

public class GameProcess {
    public void play() {
    }

    private Dealer createDealer() {
        return null;
    }

    private List<Player> createPlayers() {
        return null;
    }

    private void handOutTwoCardsToAllUsers(User dealer, List<User> users) {

    }

    private void handOutTwoCardsToUser(User user) {

    }

    private boolean shouldDealerGetCard(User dealer) {
        return true;
    }

    private boolean doPlayerGetCard(User player, String whetherPlayerReceiveCard) {
        return true;
    }
}
