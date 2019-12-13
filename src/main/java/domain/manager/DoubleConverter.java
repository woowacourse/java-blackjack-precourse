package domain.manager;

import view.output.Output;

public class DoubleConverter {
    private static final double DEFAULT_VALUE = 0;

    public Double stringToDouble(String num) {
        try {
            return Double.parseDouble(num);
        } catch (NumberFormatException e) {
            new Output().showMessageTypeMismatching();
        }

        return DEFAULT_VALUE;
    }
}
