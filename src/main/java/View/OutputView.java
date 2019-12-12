package View;

import domain.user.User;

import java.util.List;

public class OutputView {
    private static final String DELIMITER = " : ";

    public OutputView() { }

    public static void printUserCards(List<User> users) {
        for (User user : users) {
            System.out.println(user.getName() + DELIMITER + user.getCards());
        }
    }
}
