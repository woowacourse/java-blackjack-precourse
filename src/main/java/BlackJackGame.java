import java.util.*;

import domain.gameElement.*;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;

public class BlackJackGame {
    private List<User> users = new ArrayList<User>();
    private int step = 1;

    public static void main(String[] args) {
        BlackJackGame blackJackGame = new BlackJackGame();
        blackJackGame.playGame();
    }

    private void playGame() {
        boolean loop = false;
        startGame();
        proceedGame();
        endGame();
    }

    private void startGame() {
        users.add(new Dealer());
        List<String> playerNameList = new PlayersNaming().playerNaming();
        PlayersSetBettingMoney playersSetBettingMoney = new PlayersSetBettingMoney();
        for (String playerName : playerNameList) {
            users.add(new Player(playerName, playersSetBettingMoney.playerSetBettingMoney(playerName)));
        }
    }

    private void proceedGame() {
        System.out.println("\n딜러와 " + new PlayersNaming().getPlayerNamesALine(users) + "에게 2장의 카드를 나누었습니다.");
        new HandOutCard().firstHandOutCards(users, step);
        if (new NextProceedGame().nextProceed(users) == true) {
            step++;
            new HandOutCard().proceedHandOutCards(users, step);
        }
    }

    private void endGame() {
        ProfitCalculation profitCalculation = new ProfitCalculation();
        UsersTotal usersTotal = new UsersTotal();
        profitCalculation.countProfit(users, step);
        usersTotal.usersTotalCards(users);
        usersTotal.usersTotalProfit(users);
    }
}
