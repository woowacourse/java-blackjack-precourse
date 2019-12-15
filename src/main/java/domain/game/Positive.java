package domain.game;

public class Positive {
    private final double number;

    public Positive(String value) {
        this(Double.parseDouble(value));
    }

    public Positive(double number) {
        if (number < 0) {
            throw new RuntimeException("음수가 될 수 없습니다.");
        }
        this.number = number;
    }

    public double getNumber() {
        return number;
    }
}
