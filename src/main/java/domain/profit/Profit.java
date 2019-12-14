package domain.profit;

public class Profit {
	private final String name;
	private final double profit;

	public Profit(String name, double profit) {
		this.name = name;
		this.profit = profit;
	}

	public String getName() {
		return name;
	}

	public double getProfit() {
		return profit;
	}
}
