package domain.user;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends User {
	private static final String NAME = "딜러";
	private static final int DEALER_STAND_SCORE = 16;

	public Dealer() {
	}

	public static String getNAME() {
		return NAME;
	}

	public String getCardsWithName() {
		return NAME + DELIMITER + getCards();
	}

	public boolean isLessThanSeventeen() {
		return sumCardScore() <= DEALER_STAND_SCORE;
	}

}
