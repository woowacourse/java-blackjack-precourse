package domain.profit;

import java.util.List;

import domain.user.Dealer;
import domain.user.Player;

public class ProfitCalculator {
	private final Dealer dealer;
	private final List<Player> players;

	public ProfitCalculator(Dealer dealer, List<Player> players) {
		this.dealer = dealer;
		this.players = players;
	}
}
