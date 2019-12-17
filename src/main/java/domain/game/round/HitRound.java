package domain.game.round;

import controller.InputController;
import domain.game.Rule;
import domain.game.Table;
import domain.user.Gambler;
import domain.user.Player;
import view.OutputView;

public class HitRound extends Round {

	@Override
	public void run(Table table) {
		doPlayersPhase(table);
		doDealerPhase(table);
		table.setWinners(table.getDealer().sumCardsMax());
		printStatus(table);
		doSettlement(table);
	}

	@Override
	protected void doSettlement(Table table) {
		table.doSettlement(Rule.RATIO_WIN,Rule.RATIO_LOSE);
	}

	private void doPlayersPhase(Table table) {
		for (Gambler player : table.getPlayerList()
		) {
			doPlayerPhase(table, (Player)player);
		}
	}

	private void doPlayerPhase(Table table, Player player) {
		if (checkPlayerCondition(player)) {
			return;
		}
		try {
			table.drawCards(player, 1);
			printPlayerDrawText(player);
			doPlayerPhase(table,player);
		} catch (Exception e) {
			System.out.println(Rule.OUT_OF_CARDS_MESSAGE);
		}
	}

	private boolean checkPlayerCondition(Player player) {
		if (player.isBust(Rule.BLACKJACK_POINT)) {
			return true;
		}
		InputController inputController = InputController.getInputController();
		boolean yesOrNo = inputController.getYesOrNo(player.getName());
		if (!yesOrNo) {
			printPlayerDrawText(player);
		}
		return (!yesOrNo);
	}

	private void doDealerPhase(Table table) {
		if (checkDealerCondition(table.getDealer())) {
			return;
		}
		try {
			table.drawDealer(1);
			printDealerDrawText();
			doDealerPhase(table);
		} catch (Exception e) {
			System.out.println(Rule.OUT_OF_CARDS_MESSAGE);
		}
	}

	private boolean checkDealerCondition(Gambler dealer) {
		return (dealer.sumCardsMax() > Rule.DEALER_DRAW_POINT
			|| dealer.isBust(Rule.BLACKJACK_POINT));
	}

	private void printDealerDrawText() {
		OutputView outputView = OutputView.getInstance();
		outputView.printDealerDrawLine(Rule.DEALER_DRAW_POINT);
		outputView.printNewLine();
	}

	private void printPlayerDrawText(Player player) {
		OutputView outputView = OutputView.getInstance();
		outputView.printPlayerCards(player);
		outputView.printNewLine();
	}
}
