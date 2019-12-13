package domain.manager;

import java.util.List;

public class Validator {
    public boolean isContainsSpace(List<String> items) {
        return items.stream().anyMatch(x -> x.contains(" "));
    }

    public boolean hasOverlap(List<String> items) {
        return items.size() != items.stream().distinct().count();
    }

    public boolean isBelowZero(Double bettingMoney) {
        return bettingMoney <= 0;
    }
}
