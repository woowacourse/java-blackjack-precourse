package domain.user;

/**
 * 게임 참여자를 의미하는 객체
 *  기본 생성자를 추가할 수 없다
 *  필드의 private를 변경할 수 없다
 *  Card에 필드를 추가할 수 없다
 */
public class Player extends Gamer {
    private final String name;
    private final double bettingMoney;

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public String getName() {
        return name;
    }

    public double getBettingMoney() {
        return bettingMoney;
    }

    // TODO 추가 기능 구현

}
