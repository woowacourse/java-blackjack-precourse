package domain.user;

public class BettingMoney {
    private static final double BAT_NOTHING = 0;
    private static final double BLACK_JACK_RATE = 1.5;
    private static final double PUSH_MONEY = 0;
    private static final double LOSE_RATE = -1;

    private final Double money;

    public BettingMoney(Double money) {
        validateMoney(money);
        this.money = money;
    }

    private void validateMoney(Double money) {
        if (money <= BAT_NOTHING) {
            throw new IllegalArgumentException("최소 0원 이상 배팅하세요.");
        }
    }

    public double blackJack() {
        return money * BLACK_JACK_RATE;
    }

    public double push() {
        return PUSH_MONEY;
    }

    public double earn() {
        return money;
    }

    public double lose() {
        return money * LOSE_RATE;
    }
}
