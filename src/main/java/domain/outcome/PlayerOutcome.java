package domain.outcome;

public class PlayerOutcome extends Outcome {
    private final OutcomeType outcomeType;

    PlayerOutcome(String name, double bettingMoeny, OutcomeType outcomeType) {
        super(name, PlayerOutcome.calcurateBenefit(bettingMoeny, outcomeType));
        this.outcomeType = outcomeType;
    }

    private static int calcurateBenefit(double bettingMoney, OutcomeType outcomeType) {
        return (int) (bettingMoney * outcomeType.getMultipleValue());
    }
}
