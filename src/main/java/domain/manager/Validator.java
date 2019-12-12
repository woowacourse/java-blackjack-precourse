package domain.manager;

import java.util.List;

public class Validator {
    public boolean checkName(List<String> items) {
        return isNotContainsSpace(items);
    }

    public boolean isNotContainsSpace(List<String> items) {
        return items.stream().noneMatch(x -> x.contains(" "));
    }
}
