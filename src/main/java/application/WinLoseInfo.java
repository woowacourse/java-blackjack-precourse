package application;

public enum WinLoseInfo {
	BLACKJACK,
	WIN,
	LOSE,
	DRAW,
	UNDETERMINED;
	
	private final double NO_GAIN_NO_LOSE = 0.0;
	
	public double toProfit(double bettingMoney) {
		if (this == WinLoseInfo.BLACKJACK || this == WinLoseInfo.WIN) {
			return win(bettingMoney);
		}
		return notWin(bettingMoney);
	}
	
	private double win(double bettingMoney) {
		if (this == WinLoseInfo.BLACKJACK) {
			return 1.5 * bettingMoney;
		} 
		return bettingMoney;
	}
	
	private double notWin(double bettingMoney) {
		if (this == WinLoseInfo.LOSE) {
			return -bettingMoney;
		} 
		return NO_GAIN_NO_LOSE;
	}
}
