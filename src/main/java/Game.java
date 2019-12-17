import domain.user.Dealer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private ArrayList<Player> players;
    private Dealer dealer;

    public Game(String[] users) {
        int betMoney;
        Scanner scan = new Scanner(System.in);
        players = new ArrayList<>();

        for(String user:users){
            System.out.println("input the "+user+"'s betting money");
            betMoney = scan.nextInt();
            players.add(new Player(user, betMoney));
        }
    }
}
