package domain.user;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends Gamer {
    private final String name;
    private final double bettingMoney;

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }


    // TODO 추가 기능 구현

    @Override
    public String toString() {
        return "Player{" +
                "name=" + name +
                ", bettingMoney=" + bettingMoney +
                '}';
    }

}
