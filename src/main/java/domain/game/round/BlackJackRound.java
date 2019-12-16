package domain.game.round;

import java.util.Arrays;

import controller.OutputController;
import domain.game.Rule;
import domain.game.Table;
import domain.user.Gambler;
import domain.user.Player;

public class BlackJackRound extends Round {
	public BlackJackRound(Table table) {
		super(table);
	}

	@Override
	public void run() {
		distribute(Rule.getBasicDraw());
		findWinner();
		showStatus();
		if (hasWinners()) {
			super.showStatus();
			doSettlement();
		}
	}

	@Override
	void findWinner() {
		for (Gambler player : table.getPlayerList()
		) {
			player.setWinner((player.sumCardsMax() == Rule.getBlackjackPoint()));
		}
		checkDealerWin(table.getDealer(), Rule.getBlackjackPoint());
		checkPlayerWin();
	}

	@Override
	protected void showStatus() {
		OutputController outputController = OutputController.getOutputController();
		outputController.printBlackJackRoundTextLine(table.getPlayerNames(), Rule.getBasicDraw());
		outputController.printDealerCards(table.getDealer(), Rule.getOpenCount(), false);
		outputController.printNewLine();
		for (Gambler player : table.getPlayerList()
		) {
			outputController.printPlayerCards((Player)player);
			outputController.printNewLine();
		}
		outputController.printNewLine();
	}

	@Override
	protected void doSettlement() {
		if (dealerWin) {
			dealerSettlement();
			return;
		}
		doPlayerSettlement();
	}

	private void doPlayerSettlement() {
		double dealerSum = 0;
		for (Gambler gambler : table.getPlayerList()
		) {
			Player player = (Player)gambler;
			if (player.isWinner()) {
				dealerSum -= player.getBettingMoney();
				player.setEarnings(Rule.RATIO_WIN_BLACKJACK *player.getBettingMoney());
			}
		}
		table.getDealer().setEarnings(dealerSum);
	}

	private void dealerSettlement() {
		double dealerSum = 0;
		for (Gambler gambler : table.getPlayerList()
		) {
			Player player = (Player)gambler;
			if (!player.isWinner()) {
				dealerSum += player.getBettingMoney();
				player.setEarnings(Rule.RATIO_LOSE * player.getBettingMoney());
			}
		}
		table.getDealer().setEarnings(dealerSum);
	}

	private void distribute(int count) {
		try {
			table.drawAll(Arrays.asList(table.getDealer()), count);
			table.drawAll(table.getPlayerList(), count);
		} catch (Exception e) {
			System.out.println(Rule.OUT_OF_CARDS_MESSAGE);
		}
	}
}
