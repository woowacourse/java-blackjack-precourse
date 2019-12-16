package domain.user;

public class Dealer extends Gamer {
	public Dealer() {
	}

	public boolean isBlackJack() {
		if (getScoreAceAsOne() == 21 || getScoreAceAsEleven() == 21) {
			return true;
		}
		return false;
	}
}
