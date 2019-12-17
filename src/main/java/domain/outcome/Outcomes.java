package domain.outcome;

import java.util.ArrayList;
import java.util.List;

public class Outcomes {
    private static final String DEALER = "Dealer";
    private static final int ZERO = 0;

    private final List<Outcome> outcomes = new ArrayList<>();

    public Outcomes() {
        addDealer();
    }

    public void addOutcomes(String name, double benefit) {
        outcomes.add(new Outcome(name, benefit));
    }

    public boolean isHavePlayer(String name) {
        return outcomes.stream()
                .anyMatch(outcome -> outcome.isNameMatch(name));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Outcome outcome : outcomes) {
            sb.append(outcome.printOutcome());
        }
        return sb.toString();
    }

    private void addDealer() {
        outcomes.add(new Outcome(DEALER, ZERO));
    }

    private Outcome searchDealerOutcome() {
        return outcomes.stream()
                .filter(Outcome::isDealer)
                .findFirst()
                .get();
    }

    public void calcurateDealerBenefit() {
        double playerSum = outcomes.stream()
                .map(Outcome::getBenefit)
                .reduce(Double::sum)
                .get();
        searchDealerOutcome().setDealerBenefit(playerSum);
    }
}
