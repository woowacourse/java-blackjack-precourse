package domain.result;

import domain.user.Player;

public class Result {
	private final Player player;
	private final GameResult result;
	private final double benefit;

	public Result(Player player, GameResult result) {
		this.player = player;
		this.result = result;
		this.benefit = calculateBenefit();
	}

	public Player getPlayer() {
		return player;
	}

	public GameResult getResult() {
		return result;
	}
	
	public double getBenefit() {
		return benefit;
	}
	
	private double calculateBenefit() {
		if(result == GameResult.USER_WIN_WITH_BLACKJACK) {
			return player.getBettingMoney() * 1.5;
		}
		if(result == GameResult.USER_WIN) {
			return player.getBettingMoney() * 1.0;
		}
		if(result == GameResult.USER_LOSE) {
			return player.getBettingMoney() * (-1.0);
		}
		return 0;
	}
}
