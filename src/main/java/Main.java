import domain.user.Dealer;
import domain.user.Player;
import utils.UserInput;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        BlackJackGame blackJackGame = new BlackJackGame(enrollPlayers(), new Dealer());
    }

    private static List<Player> enrollPlayers() {
        List<Player> players = new ArrayList<>();
        UserInput.inputPlayersName().forEach(x -> players.add(new Player(x, getBettingMoney(x))));
        return players;
    }

    private static int getBettingMoney(String name) {
        return UserInput.inputBettingMoney(name);
    }
}
