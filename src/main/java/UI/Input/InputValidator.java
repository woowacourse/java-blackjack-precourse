package UI.Input;

public class InputValidator {
    private static final int EMPTY_NAME_LENGTH = 0;

    private static final String VALIDATE_IF_GET_CARD_REGEX = "[ny]";
    private static final String VALIDATE_IF_GET_CARD_EXCEPTION_MESSAGE = "n이나 y만 입력해주세요.";

    public static void validatePlayerNamesSplit(String[] playerNamesSplit) {
        for (String playerName : playerNamesSplit) {
            validatePlayerName(playerName.trim());
        }
    }

    private static void validatePlayerName(String name) {
        if (name.length() == EMPTY_NAME_LENGTH) {
            throw new IllegalArgumentException("자동차의 이름은 공백일 수 없습니다.");
        }
    }

    public static void validateBettingMoney(Double bettingMoney) {
        if (bettingMoney <= 0) {
            throw new IllegalArgumentException("배팅 금액은 양수만 가능합니다.");
        }
    }

    public static void validateIfGetCard(String input) {
        if (!input.matches(VALIDATE_IF_GET_CARD_REGEX)) {
            throw new IllegalArgumentException(VALIDATE_IF_GET_CARD_EXCEPTION_MESSAGE);
        }
    }
}
