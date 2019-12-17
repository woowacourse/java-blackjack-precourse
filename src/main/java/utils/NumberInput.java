package utils;

public class NumberInput {
    private static final double MIN_NUMBER = 0;
    private double number = 0;

    public NumberInput(String string) {
        System.out.println("\n" + string + "의 배팅 금액은?");
        input();
    }

    private void input() {
        double num = 0;

        try {
            num = Double.valueOf(UserInput.input());
            validate(num);
            this.number = num;
        } catch (RuntimeException e) {
            System.out.println("올바른 숫자를 입력하세요.");
            input();
        }
    }

    private void validate(double num) {
        if (num <= MIN_NUMBER) {
            throw new IllegalArgumentException();
        }
    }

    public double getNumber() {
        return this.number;
    }
}
