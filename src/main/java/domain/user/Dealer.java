package domain.user;

/**
 * 게임 딜러를 의미하는 객체
 * 기본성상자이외 다른 생성자를 추가할 수 없다.
 * 필드인 card의 접근 제어자 private을 변경할 수 없다.
 * Dealer에 필드를 추가할 수 없다.
 */
public class Dealer extends Gamer {

    final private String DEALER_NAME = "dealer";

    public Dealer() {
    }

    public String getName() {
        return DEALER_NAME;
    }

}
