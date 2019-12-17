package domain.user;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends Helper {
    private final String name;
    private final double bettingMoney;
    private final int BLACKJACK_SCORE = 21;

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    // TODO 추가 기능 구현

    public String getName() {
        return name;
    }

    public double getBettingMoney() {
        return bettingMoney;
    }

    public String cardsToString() {
        return name + "카드 : " + super.cardsToString();
    }
}
