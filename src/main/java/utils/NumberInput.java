package utils;

public class NumberInput {
	private static final double MIN_NUMBER = 0;
	private double number = 0;
	
	public NumberInput() {
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
		} catch (RuntimeException e) {
			System.out.println("올바른 숫자를 입력하세요.");
			input();
		}
		
		this.number = num;
	}
	
	private void validate(double num) {
		if (num <= MIN_NUMBER) {
			throw new IllegalArgumentException();
		}
	}
}
