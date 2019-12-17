package exception;

public class InvalidException extends IllegalArgumentException {

    public static String NOT_A_NUMBER_BETTING_EXCEPTION = "배팅금액은 숫자만 입력가능합니다.";
    public static String NEGATIVE_BETTING_EXCEPTION = "배팅금액은 0이하일 수 없습니다.";
    public static String PLAYER_NAME_EMPTY_EXCEPTION = "플레이어 이름은 비어있을 수 없습니다.";

    public InvalidException() {
    }

    public InvalidException(String errorMessage) {
        super(errorMessage);
    }

}
