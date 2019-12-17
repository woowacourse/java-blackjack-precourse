package domain.user;

/**
 * @author : Kim SeYun
 * @ClassName : Player
 * @ClassExplanation : 플레이어를 의미하는 클래스
 * @Date : 2019. 12. 17
 * @Copyright (c) 2019 SeYun Kim
 */
public class Player extends Helper {
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

    public String cardsToString() {
        return name + "카드 : " + super.cardsToString();
    }
}
