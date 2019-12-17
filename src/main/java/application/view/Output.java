package application.view;

import application.domain.user.User;

public class Output {
    public static void printFirstCardInfo(User user) {
        String playersFirstCardInfo = user.getFirstShuffleCardInfo();
        System.out.println(playersFirstCardInfo);
    }

    public static void printAllCardsInfo(User user) {
        user.getAllCardsInfo();
    }
}
