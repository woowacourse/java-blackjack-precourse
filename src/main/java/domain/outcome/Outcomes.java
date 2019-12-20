package domain.outcome;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Outcomes {
    private static final String DEALER = "Dealer";

    private final List<Outcome> outcomes = new ArrayList<>();

    public List<Outcome> getOutcomes() {
        return Collections.unmodifiableList(outcomes);
    }

//    public void addOutcomes(String name, double benefit) {
//        outcomes.add(new Outcome(name, benefit));
//    }

    public void addPlayerOutcomes(String name, double bettingMoney, OutcomeType outcomeType) {
        outcomes.add(new PlayerOutcome(name, bettingMoney, outcomeType));
    }

    public boolean isHavePlayer(String name) {
        return outcomes.stream()
                .anyMatch(outcome -> outcome.isNameMatch(name));
    }

    // TODO : 이거 나중에 추가해줘야한다.
    public void addDealerOutcome() {
        outcomes.add(new DealerOutcome(calcurateDealerBenefit()));
    }

    private double calcurateDealerBenefit() {
        return outcomes.stream()
                .map(Outcome::getBenefit)
                .reduce(Double::sum)
                .get();
    }
}
