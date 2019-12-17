package utils;

/**
 * 플레이어의 배팅 금액을 받는 객체
 */
public class NumberInput {
    private double number = 0;

    public NumberInput(String string) {
        System.out.println("\n" + string + "의 배팅 금액은?");
        input();
    }

    public double getNumber() {
        return this.number;
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
        if (isNagative(num)) {
            throw new IllegalArgumentException();
        }
    }
    
    private boolean isNagative(double num) {
        return num <= 0;
    }
}
