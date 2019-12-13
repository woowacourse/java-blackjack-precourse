package domain.exception;

import java.util.List;

public class InputException {
    final static int ZERO = 0;

    public boolean isZeroLength(List<String> splitedName) {
        if (splitedName.size() == ZERO) {
            return true;
        }

        return splitedName.stream().anyMatch(x-> x.equals(""));
    }

}
