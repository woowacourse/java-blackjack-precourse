package domain.rule;

import domain.user.Dealer;
import domain.user.User;

public class Rule {
	private static final int FIRST_DRAW_CARD_SIZE = 2;
	private static final int BEST_SCORE = 21;
	private static final int DEALER_STAY_SCORE = 17;

	public static boolean isBlackjack(User user) {
		return user.isCardSize(FIRST_DRAW_CARD_SIZE) && user.isScore(BEST_SCORE);
	}

	public static boolean isBust(User user) {
		return user.isScoreGreaterThan(BEST_SCORE);
	}

	public static boolean isPush(User dealer, User player) {
		return dealer.isScore(player.getSumOfCards());
	}

	public static boolean canGetMoreCards(User user) {
		return user.isScoreLessThan(BEST_SCORE);
	}

	public static boolean shouldAddCard(User user) {
		return user.isScoreLessThan(DEALER_STAY_SCORE);
	}
}
