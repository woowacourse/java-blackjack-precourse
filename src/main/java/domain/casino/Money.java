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
		for (Player player : playerList) {
			if (!dealer.bust()) {
				winAsBlackJack(player);
				loseBurst(player);
				winDealer(player, dealer);
				drawWithDealerAsBlackJack(player, dealer);
				drawWithDealer(player, dealer);
				loseToDealer(player, dealer);
			}
			loseBurst(player);
			OutputView.printPlayerEarning(player.getName(), earning.get(player.getName()));
		}
	}

	public void calculateDealerEarning(List<Player> playerList) {
		double dealerMoney = earning.get("딜러");
		double sum = 0;
		for (Player player : playerList) {
			sum += earning.get(player.getName());
		}

		dealerMoney -= sum;
		earning.put("딜러", dealerMoney);
		OutputView.printPlayerEarning("딜러", dealerMoney);
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
