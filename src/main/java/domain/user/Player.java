package domain.user;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends Contender {
    private final String name;
    private final double bettingMoney;

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getBettingMoney() {
        return bettingMoney;
    }

}
