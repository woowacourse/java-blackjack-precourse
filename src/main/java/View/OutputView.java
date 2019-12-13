package View;

import domain.user.Dealer;
import domain.user.User;

import java.util.List;

public class OutputView {
    private static final String DELIMITER = " : ";

    public OutputView() { }

    public static void printCardsOfOneUser(User user) {
        System.out.println(user.getName() + DELIMITER + user.getCards());
    }

    public static void printCardsOfOneDealerExceptOneCard(Dealer dealer) {
        System.out.println(dealer.getName() + DELIMITER + dealer.getCardsExceptOneCard());
    }

    public static void printCardsOfAllUsers(List<User> users) {
        for (User user : users) {
            printCardsOfOneUser(user);
        }
    }
}
