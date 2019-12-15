package application;

public enum WinLoseInfo {
	BLACKJACK(1.5),
	WIN(1.0),
	LOSE(-1.0),
	DRAW(0.0),
	UNDETERMINED(0.0);
	
	private double coefficient;
	
	WinLoseInfo(double input){
		this.coefficient = input;
	}
	
	public double toProfit(double bettingMoney) {
		return this.coefficient * bettingMoney;
	}

}
