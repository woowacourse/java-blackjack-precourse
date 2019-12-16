package domain.user;

public class Dealer extends Gamer {
	public Dealer() {
	}

	public boolean isBlackJack() {
		if (getScoreAceAsOne() == JACKPOT || getScoreAceAsEleven() == JACKPOT) {
			return true;
		}
		return false;
	}
}
