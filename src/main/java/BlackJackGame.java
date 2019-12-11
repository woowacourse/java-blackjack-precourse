import domain.user.Dealer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.List;

public class BlackJackGame {
    private final List<Player> players;
    private final Dealer dealer;

    public BlackJackGame(List<Player> players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }
}
