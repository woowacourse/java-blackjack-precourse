import domain.user.Dealer;
import domain.user.Player;
import view.UserInput;

import java.util.ArrayList;
import java.util.List;

import static view.ConsoleOutput.printMessage;

/**
 * Main.java
 * 메인 클래스
 * 우아한테크코스 프리코스 3주차
 * Original code https://github.com/hotheadfactory/java-blackjack-precourse
 * Version: v0.0.1, 2019.12.16 (c) 정회형
 */
public class Main {
    public static void main(String[] args) {
        new BlackJackGame(enrollPlayers(), new Dealer());
    }

    private static List<Player> enrollPlayers() {
        List<Player> players = new ArrayList<>();
        try {
            UserInput.inputPlayersName().forEach(x -> players.add(new Player(x, getBettingMoney(x))));
            return players;
        } catch (IllegalArgumentException e) {
            printMessage(e.getMessage());
            return enrollPlayers();
        }
    }
    private static int getBettingMoney(String name) {
        try {
            System.out.println(name + "의 배팅 금액은?");
            return UserInput.inputBettingMoney();
        } catch (IllegalArgumentException e) {
            printMessage("다시 입력해 주세요.");
            return getBettingMoney(name);
        }
    }
}
