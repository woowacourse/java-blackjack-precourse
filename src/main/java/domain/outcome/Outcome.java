package domain.outcome;

public class Outcome {
    private static final int CHANGE_SIGN = -1;
    private static final String DOUBLE_COLON = " : ";
    private static final String NEW_LINE = "\n";
    private static final String DEALER = "Dealer";

    private final String name;
    private int benefit;

    Outcome(String name, double benefit) {
        this.name = name;
        this.benefit = (int) benefit;
    }

    String printOutcome() {
        return name +
                DOUBLE_COLON +
                benefit +
                NEW_LINE;
    }

    double getBenefit() {
        return benefit;
    }

    void setDealerBenefit(double benefit) {
        if (!isDealer()) {
            throw new IllegalStateException(
                    "Dealer가 아니면 benefit을 변경할 수 없습니다."
            );
        }
        this.benefit = (int) benefit * (CHANGE_SIGN);
    }

    boolean isNameMatch(String name) {
        return this.name.equals(name);
    }

    boolean isDealer() {
        return this.name.equals(DEALER);
    }
}
