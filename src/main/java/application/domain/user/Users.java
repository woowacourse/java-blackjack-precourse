package application.domain.user;

import java.util.List;

public class Users {
    private final List<Player> players;
    private final Dealer dealer;

    public Users(List<Player> players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    @Override
    public String toString() {
        return "Users{" +
                "players=" + players;// +
                //", dealer=" + dealer +
                //'}';
    }
}
