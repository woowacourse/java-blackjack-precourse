package domain.casino;

import java.util.HashMap;
import java.util.List;

import domain.user.Dealer;
import domain.user.Player;
import domain.view.OutputView;

public class Money {

	private HashMap<String, Double> earning = new HashMap<>();

	public Money(List<Player> playerList) {
		earning.put("딜러", 0.0);
		for (Player player : playerList) {
			earning.put(player.getName(), player.getBettingMoney());
		}
	}

	public void calculatePlayersEarning(Dealer dealer, List<Player> playerList) {
		if (!dealer.bust()) {
			playerList.forEach(p -> winDealer(p, dealer));
			playerList.forEach(p -> drawWithDealerAsBlackJack(p, dealer));
			playerList.forEach(p -> drawWithDealer(p, dealer));
			playerList.forEach(p -> loseToDealer(p, dealer));
		}
		playerList.forEach(this::winAsBlackJack);
		playerList.forEach(this::loseBurst);
	}

	public void calculateDealerEarning(List<Player> playerList) {
		double dealerEarning = earning.get("딜러");
		double sumOfPlayerEarnings = playerList.stream()
			.map(player -> earning.get(player.getName()))
			.reduce(Double::sum)
			.get();

		dealerEarning -= sumOfPlayerEarnings;
		earning.put("딜러", dealerEarning);

	}

	public void printPlayersEarning(List<Player> playerList) {
		playerList.forEach(p -> OutputView.printEarning(p.getName(), earning.get(p.getName())));
	}

	public void printDealerEarning() {
		OutputView.printEarning("딜러", earning.get("딜러"));
	}

	private void winAsBlackJack(Player player) {
		if (player.isBlackJack()) {
			earning.put(player.getName(), player.getBettingMoney() * 1.5);
		}
	}

	private void loseBurst(Player player) {
		if (player.bust()) {
			earning.put(player.getName(), -player.getBettingMoney());
		}
	}

	private void winDealer(Player player, Dealer dealer) {
		if (!player.isBlackJack() && player.sumCardScore() <= 21 && player.sumCardScore() > dealer.sumCardScore()) {
			earning.put(player.getName(), player.getBettingMoney());
		}
	}

	private void drawWithDealerAsBlackJack(Player player, Dealer dealer) {
		if (player.isBlackJack() && dealer.isBlackJack()) {
			earning.put(player.getName(), 0.0);
		}
	}

	private void drawWithDealer(Player player, Dealer dealer) {
		if (!player.isBlackJack() && player.sumCardScore() == dealer.sumCardScore()) {
			earning.put(player.getName(), 0.0);
		}
	}

	private void loseToDealer(Player player, Dealer dealer) {
		if (player.sumCardScore() <= 21 && player.sumCardScore() < dealer.sumCardScore()) {
			earning.put(player.getName(), -player.getBettingMoney());
		}
	}
}
