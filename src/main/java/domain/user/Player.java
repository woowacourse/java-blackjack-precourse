package domain.user;

public class Player extends User{
	private final String name;
	private final double bettingMoney;

	public Player(String name, int bettingMoney) {
		this.name = name;
		this.bettingMoney = bettingMoney;
	}

	public static void checkValidName(String name) {
		PlayerConstraints.checkEmptyName(name);
	}

	public static void checkValidBettingMoney(int money) {
		PlayerConstraints.checkMoneyRange(money);
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getBettingMoney() {
		return this.bettingMoney;
	}
}
