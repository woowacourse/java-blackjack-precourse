package domain;

public class BettingMoney {

    private static final double BETTING_NOTHING = 0;
    private static final double LOSE_RATE = -1;
    private static final double BLACK_JACK_RATE = 1.5;

    private final double bettingMoney;

    public BettingMoney( double bettingMoney ) {
        validateBettingMoney(bettingMoney);
        this.bettingMoney = bettingMoney;
    }

    private void validateBettingMoney( double bettingMoney ) {
        if (bettingMoney <= BETTING_NOTHING) {
            throw new IllegalArgumentException("최소 0원 이상 배팅하세요");
        }
    }

    public double blackJack() {
        return BLACK_JACK_RATE * bettingMoney;
    }

    public double earn() {
        return bettingMoney;
    }

    public double loseGame() {
        return LOSE_RATE * bettingMoney;
    }

}
