package domain;

public enum BenefitMultipleType {
    INIT_USER_BLACKJACK(0.5),
    INIT_BOTH_BLACKJACK(0),
    USER_WIN(1.0),
    USER_LOSE(-1.0);

    private double multipleValue;

    BenefitMultipleType(double multipleValue) {
        this.multipleValue = multipleValue;
    }

    public double getMultipleValue() {
        return multipleValue;
    }
}
