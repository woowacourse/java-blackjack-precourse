package domain.user;

import static domain.card.CardBundle.BLACK_JACK;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends Gamer {
    private final String name;
    private final BettingMoney bettingMoney;

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = new BettingMoney(bettingMoney);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean canReceive() {
        return getScore() < BLACK_JACK;
    }

}
