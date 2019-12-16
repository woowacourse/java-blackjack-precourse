package domain;

public enum BenefitMultipleType {
    INIT_USER_BLACKJACK(1.5),
    INIT_BOTH_BLACKJACK(1.0),
    USER_WIN(2.0),
    USER_LOSE(-1.0);

    private double multipleValue;

    BenefitMultipleType(double multipleValue) {
        this.multipleValue = multipleValue;
    }

    public double getMultipleValue() {
        return multipleValue;
    }
}
