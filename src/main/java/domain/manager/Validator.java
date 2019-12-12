package domain.manager;

import java.util.List;

public class Validator {
    public boolean isNotContainsSpace(List<String> items) {
        return items.stream().noneMatch(x -> x.contains(" "));
    }
}
