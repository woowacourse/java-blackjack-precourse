package exception;

public class InvalidException extends IllegalArgumentException {

    public static String NOT_A_NUMBER_BATTING_EXCEPTION = "배팅금액은 숫자만 입력가능합니다.";

    public InvalidException() {
    }

    public InvalidException(String errorMessage) {
        super(errorMessage);
    }

}
