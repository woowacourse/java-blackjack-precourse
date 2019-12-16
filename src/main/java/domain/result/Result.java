package domain.result;

import domain.user.Player;

public class Result {
	private static final double JACKPOT_WIN_MULTIPLY_RATE = 1.5;
	private static final double WIN_MULTIPLY_RATE = 1.0;
	private static final double LOSE_MULTIPLY_RATE = -1.0;
	private static final double TIE_MULTIPLY_RATE = 0;
	private final Player player;
	private final GameResult result;

	public Result(Player player, GameResult result) {
		this.player = player;
		this.result = result;
	}

	public Player getPlayer() {
		return player;
	}

	public GameResult getResult() {
		return result;
	}

	public double getBenefit() {
		if (result == GameResult.USER_WIN_WITH_BLACKJACK) {
			return player.getBettingMoney() * JACKPOT_WIN_MULTIPLY_RATE;
		}
		if (result == GameResult.USER_WIN) {
			return player.getBettingMoney() * WIN_MULTIPLY_RATE;
		}
		if (result == GameResult.USER_LOSE) {
			return player.getBettingMoney() * LOSE_MULTIPLY_RATE;
		}
		return TIE_MULTIPLY_RATE;
	}
}
