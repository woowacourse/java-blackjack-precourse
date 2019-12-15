package domain.user;


/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends Dealer {
    /*
     * Player 클래스 입니다.
     * 현재 Dealer 클래스를 상속받았습니다.
     */
    private final String name;
    private final double bettingMoney;

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

}
