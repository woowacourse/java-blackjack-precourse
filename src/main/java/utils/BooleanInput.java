package utils;

public class BooleanInput {
	private static final String AGREE_CHAR = "y";
	private static final String DISAGREE_CHAR = "n";
	private boolean agree;
	
	public BooleanInput(String string) {
		System.out.println(string + "님은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
		input();
	}

	private void input() {
		String agreeString = UserInput.input();
		
		try {
			validate(agreeString);
			this.agree = stringtobool(agreeString);
		} catch (IllegalArgumentException e) {
			System.out.println("올바른 문자를 입력하세요.");
			input();
		}
	}
	
	private boolean stringtobool(String string) {
		return string.equals(AGREE_CHAR);
	}
	
	private void validate(String agreeString) {
		if (!agreeString.equals(AGREE_CHAR)  
				&& !agreeString.equals(DISAGREE_CHAR)) {
			throw new IllegalArgumentException();
		}
	}
	
	public boolean getAgree() {
		return this.agree;
	}
}
