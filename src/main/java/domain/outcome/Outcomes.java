package domain.outcome;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Outcomes {
    private static final List<Outcome> outcomes = new ArrayList<>();

    public Outcomes() {
        addDealer();
    }

    public void addOutcomes(String name, double benefit, List<Card> cards) {
        outcomes.add(new Outcome(name, benefit, cards));
    }

    public boolean isHavePlayer(String name) {
        return outcomes.stream().anyMatch(outcome -> outcome.isNameMatch(name));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Outcome outcome : outcomes) {
            sb.append(outcome.printOutcome());
        }
        return sb.toString();
    }

    private void addDealer() {
        outcomes.add(new Outcome("Dealer", 0, new ArrayList<Card>()));
    }

    private Outcome searchDealerOutcome() {
        return outcomes.stream()
                .filter(outcome -> outcome.isDealer())
                .findFirst()
                .get();
    }

    public void calcurateDealerBenefit() {
        double playerSum = outcomes.stream()
                .map(outcome -> outcome.getBenefit())
                .reduce(Double::sum)
                .get();

        searchDealerOutcome().setDealerBenefit(playerSum);
    }
}
