package domain.user;

import java.util.ArrayList;
import java.util.List;

public class Gamers {
    private final List<Gamer> gamers;

    public Gamers(Dealer dealer, List<Player> players) {
        List<Gamer> gamers = new ArrayList<>();
        gamers.add(dealer);
        gamers.addAll(players);
        this.gamers = gamers;
    }

    public void initCard(Dealer dealer) {
        for (int i = 0; i < 2; i++) {
            gamers.forEach(gamer -> gamer.addCard(dealer.pickCard()));
        }
    }
}
