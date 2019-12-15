package domain.view;

public class Announcer {

    public static final String PLAYER_INPUT_MENT = "플레이어 이름을 입력해주세요. (쉼표 기준으로 분리)";
    public static final String MONEY_INPUT_MENT = "의 베팅금액: ";
    public static final String DONE_USER_CREATE = "딜러에게 한 장의 카드, %s에게 두 장의 카드를 나눠줬습니다.";

    public void announcePlayerInput() {
        System.out.println(PLAYER_INPUT_MENT);
    }

    public void announceMoneyInput(String name) {
        System.out.println(name+MONEY_INPUT_MENT);
    }

    public void announceDoneUserCreate(String name) {
        System.out.printf(DONE_USER_CREATE, name);
    }
}
