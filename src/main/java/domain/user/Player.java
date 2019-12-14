package domain.user;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends User {
	private static final String WHITE_SPACE = " ";
	private static final int MIN_BETTING_MONEY = 1;
	private static final String NAME_EMPTY_ERROR = "이름이 비어있다.";
	private static final String NAME_WHITE_SPACE_ERROR = "이름에 공백이 존재한다.";
	private static final String BETTING_MONEY_ERROR = "베팅 금액이 " + MIN_BETTING_MONEY + " 보다 작다.";

	private final String name;
	private final double bettingMoney;

	public Player(String name, double bettingMoney) {
		validateName(name);
		validateBettingMoney(bettingMoney);
		this.name = name;
		this.bettingMoney = bettingMoney;
	}

	public static void validateName(String name) {
		if (name.isEmpty()) {
			throw new IllegalArgumentException(NAME_EMPTY_ERROR);
		}
		if (name.contains(WHITE_SPACE)) {
			throw new IllegalArgumentException(NAME_WHITE_SPACE_ERROR);
		}
	}

	public static void validateBettingMoney(double bettingMoney) {
		if (bettingMoney < MIN_BETTING_MONEY) {
			throw new IllegalArgumentException(BETTING_MONEY_ERROR);
		}
	}

	public String getName() {
		return name;
	}

	public double getBettingMoney() {
		return bettingMoney;
	}
}
