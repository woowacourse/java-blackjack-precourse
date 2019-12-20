package view.dto;

import domain.outcome.Outcome;

import java.util.List;

public class OutcomesConverter {
    private static final String DOUBLE_COLON = " : ";
    private static final String NEW_LINE = "\n";

    public static String convertOutcomesString(List<Outcome> outcomes) {
        StringBuilder sb = new StringBuilder();
        for (Outcome outcome : outcomes) {
            sb.append(convertOutcomeString(outcome));
        }
        return sb.toString();
    }

    public static String convertOutcomeString(Outcome outcome) {
        return outcome.getName() +
                DOUBLE_COLON +
                outcome.getBenefit() +
                NEW_LINE;
    }
}
