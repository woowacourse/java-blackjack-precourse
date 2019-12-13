package View;

public class InputValidator {
    private static final String VALIDATE_IF_GET_CARD_REGEX = "[ny]";
    private static final String VALIDATE_IF_GET_CARD_EXCEPTION_MESSAGE = "n이나 y만 입력해주세요.";

    public static void validateIfGetCard(String input) {
        if (!input.matches(VALIDATE_IF_GET_CARD_REGEX)) {
            throw new IllegalArgumentException(VALIDATE_IF_GET_CARD_EXCEPTION_MESSAGE);
        }
    }
}
