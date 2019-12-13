package domain.manager;

import java.util.List;

public class Validator {
    public boolean isContainsSpace(List<String> items) {
        return items.stream().anyMatch(x -> x.contains(" "));
    }

    public boolean isBelowZero(List<Double> doubles) {
        return doubles.stream().anyMatch(num -> num <= 0);
    }
}
