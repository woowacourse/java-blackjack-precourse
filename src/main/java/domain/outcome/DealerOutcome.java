package domain.outcome;

public class DealerOutcome extends Outcome {
    private static final int CHANGE_SIGN = -1;
    private static final String DEALER = "Dealer";

    DealerOutcome(double benefit) {
        super(DEALER, CHANGE_SIGN * (int) benefit);
    }
}
