import domain.user.Dealer;
import domain.user.Player;
import utils.UserInput;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import static utils.ConsoleOutput.printMessage;


public class Main {
    public static void main(String[] args) {
        BlackJackGame blackJackGame = new BlackJackGame(enrollPlayers(), new Dealer());
    }

    private static List<Player> enrollPlayers() {
        List<Player> players = new ArrayList<>();
        try {
            UserInput.inputPlayersName().forEach(x -> players.add(new Player(x, getBettingMoney(x))));
            return players;
        }catch (IllegalArgumentException e) {
            printMessage(e.getMessage());
            return enrollPlayers();
        }
    }

    private static int getBettingMoney(String name) {
        try {
            System.out.println(name+"의 배팅 금액은?");
            return UserInput.inputBettingMoney(name);
        } catch (IllegalArgumentException e) {
            printMessage("다시 입력해 주세요.");
            return getBettingMoney(name);
        }
    }
}
