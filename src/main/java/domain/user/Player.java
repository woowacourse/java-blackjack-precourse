package domain.user;

public class Player extends Gamer {
	private static final int FIRST_CARD = 0;
	private static final int SECOND_CARD = 1;
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
		int score = INIT_SCORE;
		score = aceToEleven(getCards().get(FIRST_CARD).getSymbol().getScore())
				+ aceToEleven(getCards().get(SECOND_CARD).getSymbol().getScore());
		if (score == JACKPOT) {
			return true;
		}
		return false;
	}
}
