package domain.ui;

import domain.user.*;
import java.util.List;
import java.util.Scanner;

public class UserInterfaceMachine {
    private Scanner scanner = new Scanner(System.in);

    public String[] scanNames() {
        return null;
    }

    private boolean isCorrectName() {
        return true;
    }

    public int scanBetAmountOfPlayer(String playerName) {
        return 0;
    }

    private boolean isCorrectBetAmount() {
        return true;
    }

    public String scanWhetherPlayerReceiveCard() {
        return "";
    }

    private boolean isCorrectWhetherPlayerReceiveCard() {
        return true;
    }

    public void printUsersCardsResults(Dealer dealer, List<Player> players) {

    }

    private void printUserCardsResult(User user) {

    }

    public void printUsersFinalRevenues(Dealer dealer, List<Player> players) {

    }

    private void printUserFinalRevenue(User user) {

    }
}
