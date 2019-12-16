package domain.user;

public class Player extends Gamer {
	private final String name;
	private final double bettingMoney;

	public Player(String name, double bettingMoney) {
		this.name = name;
		this.bettingMoney = bettingMoney;
	}

	public String getName() {
		return name;
	}

	public double getBettingMoney() {
		return bettingMoney;
	}

	public boolean isBlackJack() {
		int score = 0;
		score = aceToEleven(getCards().get(0).getSymbol().getScore())
				+ aceToEleven(getCards().get(1).getSymbol().getScore());
		if (score == 21) {
			return true;
		}
		return false;
	}
}
