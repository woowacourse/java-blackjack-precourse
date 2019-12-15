package domain.game;

public class Rule {
	static final int BASIC_DRAW = 2;
	static final int BLACKJACK_POINT = 21;
	static final int DEALER_DRAW_POINT = 17;
	private static final String OUT_OF_CARDS_MESSAGE = "카드가 부족합니다.";

	public static int getBasicDraw() {
		return BASIC_DRAW;
	}

	public static int getBlackjackPoint() {
		return BLACKJACK_POINT;
	}

	public static int getDealerDrawPoint() {
		return DEALER_DRAW_POINT;
	}

	public static String getOutOfCardsMessage() {
		return OUT_OF_CARDS_MESSAGE;
	}
}
