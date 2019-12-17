package domain.card;

public enum Symbol {
	ACE(1) {
		@Override
		public String getScoreText() {
			return "A";
		}
	},
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	NINE(9),
	TEN(10),
	JACK(10) {
		@Override
		public String getScoreText() {
			return "J";
		}
	},
	QUEEN(10) {
		@Override
		public String getScoreText() {
			return "Q";
		}
	},
	KING(10) {
		@Override
		public String getScoreText() {
			return "K";
		}
	};

	private int score;

	Symbol(int score) {
		this.score = score;
	}

	public int getScore() {
		return score;
	}

	public String getScoreText() {
		return Integer.toString(score);
	}
}
