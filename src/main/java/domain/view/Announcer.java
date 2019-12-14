package domain.view;

public class Announcer {

    public static final String PLAYER_INPUT_MENT = "플레이어 이름을 입력해주세요. (쉼표 기준으로 분리)";
    public static final String MONEY_INPUT_MENT = "의 베팅금액: ";

    public void announcePlayerInput() {
        System.out.println(PLAYER_INPUT_MENT);
    }

    public void announceMoneyInput(String name) {
        System.out.println(name+MONEY_INPUT_MENT);

    }
}
