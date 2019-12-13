package View;

import domain.user.User;

import java.util.List;

public class OutputView {
    private static final String DELIMITER = " : ";

    public OutputView() { }

    public static void printCardsOfAllUsers(List<User> users) {
        for (User user : users) {
            printCardsOfOneUser(user);
        }
    }

    public static void printCardsOfOneUser(User user) {
        System.out.println(user.getName() + DELIMITER + user.getCards());
    }
}
