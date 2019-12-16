package domain.outcome;

import java.util.List;

public class Outcomes {
    private final List<Outcome> outcomes;

    public Outcomes(List<Outcome> outcomes) {
        this.outcomes = outcomes;
    }

    public void addOutcomes(Outcome userOutcome) {
        outcomes.add(userOutcome);
    }

    public boolean isHavePlayer(String name) {
        return outcomes.stream().anyMatch(outcome -> outcome.isNameMatch(name));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Outcome outcome: outcomes) {
            sb.append(outcome.getName() + " : " + outcome.getBenefit()).append("\n");
        }
        return sb.toString();
    }
}
