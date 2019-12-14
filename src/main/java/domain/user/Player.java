package domain.user;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends User {
	private static final String WHITE_SPACE = " ";
	private static final int MIN_BETTING_MONEY = 1;

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
			throw new IllegalArgumentException();
		}
		if (name.contains(WHITE_SPACE)) {
			throw new IllegalArgumentException();
		}
	}

	public static void validateBettingMoney(double bettingMoney) {
		if (bettingMoney < MIN_BETTING_MONEY) {
			throw new IllegalArgumentException();
		}
	}

	public String getName() {
		return name;
	}

	public double getBettingMoney() {
		return bettingMoney;
	}
}
