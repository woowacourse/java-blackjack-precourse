package domain;

public enum BenefitType {
    INIT_USER_BLACKJACK(0.5),
    INIT_BOTH_BLACKJACK(0),
    USER_WIN(1.0),
    USER_LOSE(-1.0);

    private double multipleValue;

    BenefitType(double multipleValue) {
        this.multipleValue = multipleValue;
    }

    public double getMultipleValue() {
        return multipleValue;
    }

    public static BenefitType calcurateBlackJackBenefit(boolean isDealerBlackJack, boolean isPlayerBlackJack) {
        if (isDealerBlackJack == true && isPlayerBlackJack == true) {
            return BenefitType.INIT_BOTH_BLACKJACK;
        }
        if (isPlayerBlackJack == true) {
            return BenefitType.INIT_USER_BLACKJACK;
        }
        throw new IllegalStateException("승패가 결정되지 않음.");
    }

    public static BenefitType calcureateWinnerBenefit(boolean isWin) {
        if (isWin == true) {
            return BenefitType.USER_WIN;
        }
        if (isWin == false) {
            return BenefitType.USER_LOSE;
        }
        throw new IllegalStateException("승패가 결정되지 않음.");
    }
}
