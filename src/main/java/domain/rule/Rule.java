package domain.rule;

import domain.user.User;

public class Rule {
	private static final int FIRST_DRAW_CARD_SIZE = 2;
	private static final int BEST_SCORE = 21;

	public static boolean isBlackjack(User user) {
		return user.isCardSize(FIRST_DRAW_CARD_SIZE) && user.isScore(BEST_SCORE);
	}
}
