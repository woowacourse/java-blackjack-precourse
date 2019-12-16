package utils;

public class NumberHandler {
    public static String deleteDecimalPointZero(double number) {
        if (Double.toString(number).endsWith(".0")) {
            return Double.toString(number).replace(".0", "");
        }
        return Double.toString(number);
    }
}
