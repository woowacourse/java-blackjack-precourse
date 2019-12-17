/*
 * ErrorMessage.java
 *
 * version 1.0
 *
 * 20191216
 *
 * copyright by jiwonSong(s26788761@naver.com)
 *
 */

package domain.game;

public class ErrorMessage {
    public static final String PLAYER_IS_NOT_ENTERED = "플레이어 이름이 한명도 입력되지 않았습니다. 다시 입력해주세요.";
    public static final String PLAYER_NAME_IS_TOO_SHORT = "플레이어 이름이 너무 짧습니다. 다시 입력해주세요.";
    public static final String PLAYER_NAME_IS_DUPLICATED = "플레이어 이름이 중복되었습니다. 다시 입력해주세요.";
    public static final String PLAYER_BATTING_MONEY_CONTAINS_CHAR = "입력받은 베팅할 금액에 문자가 존재합니다. 다시 입력해주세요.";
    public static final String PLAYER_BATTING_MONEY_IS_MINUS = "입력받은 베팅할 금액이 음수입니다. 다시 입력해주세요.";
    public static final String PLAYER_BATTING_MONEY_IS_ZERO = "입력받은 베팅할 금액에 0입니다. 다시 입력해주세요.";
    public static final String INPUT_IS_NOT_YES_OR_NO = "입력된 문자가 \"y\" 또는 \"n\"가 아닙니다. 다시 입력해주세요.";
}
