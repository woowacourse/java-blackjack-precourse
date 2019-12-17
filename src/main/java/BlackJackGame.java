import java.util.*;

import domain.gameElement.*;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;

public class BlackJackGame {

    public static void main(String[] args) {
        List<User> users = new ArrayList<User>();
        BlackJackGame blackJackGame = new BlackJackGame();
        blackJackGame.playGame(users);
    }

    private void playGame(List<User> users) {
        int step = 0;
        startGame(users);
        step = proceedGame(users);
        endGame(users, step);
    }

    private void startGame(List<User> users) {
        users.add(new Dealer());
        List<String> playerNameList = new PlayersNaming().playerNaming();
        PlayersSetBettingMoney playersSetBettingMoney = new PlayersSetBettingMoney();
        for (String playerName : playerNameList) {
            users.add(new Player(playerName, playersSetBettingMoney.playerSetBettingMoney(playerName)));
        }
    }

    private int proceedGame(List<User> users) {
        System.out.println("\n딜러와 " + new PlayersNaming().getPlayerNamesALine(users) + "에게 2장의 카드를 나누었습니다.");
        int step = 1;
        new HandOutCard().firstHandOutCards(users, step);
        if (new NextProceedGame().nextProceed(users) == true) {
            step++;
            new HandOutCard().proceedHandOutCards(users, step);
        }
        return step;
    }

    private void endGame(List<User> users, int step) {
        ProfitCalculation profitCalculation = new ProfitCalculation();
        UsersTotal usersTotal = new UsersTotal();
        profitCalculation.countProfit(users, step);
        usersTotal.usersTotalCards(users);
        usersTotal.usersTotalProfit(users);
    }
}
