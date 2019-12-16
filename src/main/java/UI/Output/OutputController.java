package UI.Output;

import domain.result.GameResult;
import domain.user.User;

import java.util.Map;
import java.util.Set;

public class OutputController {
    private static final String DELIMITER = " : ";

    private static final String PREVIOUS_MARK_FOR_GAME_RESULT = "----- ";
    private static final String SUBSEQUENT_MARK_FOR_GAME_RESULT = " -----";
    private static final String SUBTITLE_FOR_GAME_RESULT = "게임 결과";

    private static final String PREVIOUS_MARK_FOR_USER_NAME = "[ ";
    private static final String SUBSEQUENT_MARK_FOR_USER_NAME = " ]";
    private static final String SUBTITLE_FOR_HOLDING_CARDS = "카드";
    private static final String SUBTITLE_FOR_SCORE_OF_CARDS = "점수";
    private static final String SUBTITLE_FOR_PROFIT = "수익";

    private OutputController() {
    }

    public static void printInitialCards(User user) {
        OutputView.printString(user.getName() + DELIMITER + user.getInitialCards());
    }

    public static void printHoldingCards(User user) {
        OutputView.printString(user.getName() + DELIMITER + user.getHoldingCards());
    }

    public static void printMessage(String message) {
        OutputView.printString(message);
    }

    public static void printGameResult(GameResult gameResult) {
        Map<User, Double> gameResultMap = gameResult.getGameResult();
        Set<User> users = gameResultMap.keySet();

        OutputView.printString(PREVIOUS_MARK_FOR_GAME_RESULT + SUBTITLE_FOR_GAME_RESULT + SUBSEQUENT_MARK_FOR_GAME_RESULT);
        for (User user : users) {
            OutputView.printString(PREVIOUS_MARK_FOR_USER_NAME + user.getName() + SUBSEQUENT_MARK_FOR_USER_NAME);
            OutputView.printString(SUBTITLE_FOR_HOLDING_CARDS + DELIMITER + user.getHoldingCards());
            OutputView.printString(SUBTITLE_FOR_SCORE_OF_CARDS + DELIMITER + user.getScoreOfCards());
            OutputView.printString(SUBTITLE_FOR_PROFIT + DELIMITER + gameResultMap.get(user));
        }
    }
}