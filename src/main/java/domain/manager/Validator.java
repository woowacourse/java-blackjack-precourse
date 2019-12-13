package domain.manager;

import java.util.List;

public class Validator {
    public boolean checkName(List<String> items) {
        return isContainsSpace(items);
    }

    public boolean isContainsSpace(List<String> items) {
        return items.stream().anyMatch(x -> x.contains(" "));
    }

}
