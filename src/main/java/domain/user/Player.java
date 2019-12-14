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

    public double getResultMoney(boolean isDealerBlackJack, boolean isWinner) {
        if (isDealerBlackJack) {
            return getBlackJackMoney();
        }
        if (isBlackJack()) {
            return bettingMoney.blackJack();
        }
        if (isWinner) {
            return bettingMoney.earn();
        }
        return bettingMoney.lose();
    }

    private double getBlackJackMoney() {
        if (isBlackJack()) {
            return bettingMoney.push();
        }
        return bettingMoney.lose();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean canReceive() {
        return getScore() < BLACK_JACK;
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    @Override
    public boolean isPlayer() {
        return true;
    }
}
