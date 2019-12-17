package exception;

public class InvalidException extends IllegalArgumentException {

    public static final String NOT_A_NUMBER_BETTING_EXCEPTION = "배팅금액은 숫자만 입력가능합니다.";
    public static final String NEGATIVE_BETTING_EXCEPTION = "배팅금액은 0이하일 수 없습니다.";
    public static final String PLAYER_NAME_EMPTY_EXCEPTION = "플레이어 이름은 비어있을 수 없습니다.";
    public static final String PLAYER_NAME_DUPLICATE_EXCEPTION = "플레이어 이름을 중복하여 입력했습니다.";
    public static final String ANSWER_SHOULD_BE_Y_OR_N_EXCEPTION = "응답은 y 또는 n만 가능합니다.";

    public InvalidException() {
    }

    public InvalidException(String errorMessage) {
        super(errorMessage);
    }

}
