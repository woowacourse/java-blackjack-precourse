package domain.ui;

import domain.user.User;
import java.util.List;
import java.util.Scanner;

public class UserInterfaceMachine {
    private Scanner scanner = new Scanner(System.in);

    public String scanName() {
        return "";
    }

    private boolean isCorrectName() {
        return true;
    }

    public int scanBetAmount() {
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

    public void printUsersCardsResults(List<User> users) {

    }

    private void printUserCardsResult(User user) {

    }

    public void printUsersFinalRevenues(List<User> users) {

    }

    private void printUserFinalRevenue(User user) {

    }
}
