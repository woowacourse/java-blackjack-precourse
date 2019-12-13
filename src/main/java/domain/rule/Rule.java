package domain.rule;

import domain.user.Gamer;

public class Rule {
	private static final int FIRST_DRAW_CARD_SIZE = 2;
	private static final int BEST_SCORE = 21;

	public static boolean isBlackjack(Gamer gamer) {
		return gamer.isCardSize(FIRST_DRAW_CARD_SIZE) && gamer.isScore(BEST_SCORE);
	}
}
