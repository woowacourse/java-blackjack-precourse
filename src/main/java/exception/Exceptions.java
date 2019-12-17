package exception;

public enum Exceptions {
    NOT_INTEGER("숫자가 아닙니다."),
    EMPTY_PLAYER_NAME("플레이어 이름은 비어있을 수 없습니다."),
    CONTAINS_BLANK("플레이어 이름은 공백을 포함할 수 없습니다."),
    CONTAINS_COMMA("플레이어 이름은 쉼표를 포함할 수 없습니다."),
    WRONG_BETTING_MONEY("배팅 금액은 0이하일 수 없습니다."),
    NOT_YES_OR_NO("y 또는 n이 아닙니다."),
    WRONG_PLAYERS_SIZE("2~8명의 플레이어만 게임 진행이 가능합니다."),
    DUPLICATED_PLAYERS_NAME("플레이어들의 이름은 중복될 수 없습니다."),
    NOT_MATCH_SIZE("카드의 수와 플레이어의 수가 일치하지 않습니다.");


    private String content;

    Exceptions(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
