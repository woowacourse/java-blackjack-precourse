package domain;

import domain.user.Gamer;
import domain.user.Dealer;
import domain.user.Player;

import java.util.List;
import java.util.ArrayList;

public class GameBoard {
    List<Gamer> gamer = new ArrayList<>();

    public GameBoard(String[] player, List<Integer> bettingMoney) {
        gamer.add(new Dealer());

        for (int i = 0; i < player.length; i++) {
            gamer.add(createPlayer(player[i], bettingMoney.get(i)));
        }

        for (Gamer g : gamer) {
            System.out.println(g.toString());
        }
    }

    public Player createPlayer(String name, int bettingMoney) {
        Player player = new Player(name, bettingMoney);

        return player;
    }

}
