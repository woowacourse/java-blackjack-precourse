package UI.Output;

import domain.user.User;

public class OutputController {
    private static final String DELIMITER = " : ";
    private static final String PREVIOUS_MARK_FOR_USER_NAME = "[ ";
    private static final String SUBSEQUENT_MARK_FOR_USER_NAME = " ]";
    private static final String SUBTITLE_FOR_HOLDING_CARDS = "카드";
    private static final String SUBTITLE_FOR_SCORE_OF_CARDS = "점";
    private static final String SUBTITLE_FOR_PROFIT = "수익";

    private OutputController() {}

    public static void printInitialCards(User user) {
        OutputView.printString(user.getName() + DELIMITER + user.getInitialCards());
    }

    public static void printHoldingCards(User user) {
        OutputView.printString(user.getName() + DELIMITER + user.getHoldingCards());
    }

    public static void printMessage(String message) {
        OutputView.printString(message);
    }

    public static void printUser(User user) {
        OutputView.printString(PREVIOUS_MARK_FOR_USER_NAME + user.getName() + SUBSEQUENT_MARK_FOR_USER_NAME);
        OutputView.printString(SUBTITLE_FOR_HOLDING_CARDS + DELIMITER + user.getHoldingCards());
        OutputView.printString(SUBTITLE_FOR_SCORE_OF_CARDS + DELIMITER + user.getScoreOfCards());
        OutputView.printString(SUBTITLE_FOR_PROFIT + DELIMITER + user.getScoreOfCards());
    }

    public static void printGameResult(User user) {
        OutputView.printString(PREVIOUS_MARK_FOR_USER_NAME + user.getName() + SUBSEQUENT_MARK_FOR_USER_NAME);
        OutputView.printString(SUBTITLE_FOR_HOLDING_CARDS + DELIMITER + user.getHoldingCards());
        OutputView.printString(SUBTITLE_FOR_SCORE_OF_CARDS + DELIMITER + user.getScoreOfCards());
        OutputView.printString(SUBTITLE_FOR_PROFIT + DELIMITER + user.getScoreOfCards());
    }
}
