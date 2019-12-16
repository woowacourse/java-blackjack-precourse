package domain.game.round;

import java.util.Comparator;
import java.util.stream.Collectors;

import controller.InputController;
import controller.OutputController;
import domain.game.Rule;
import domain.game.Table;
import domain.user.Gambler;
import domain.user.Player;

public class HitRound extends Round {
	@Override
	public void run(Table table) {
		doPlayersPhase(table);
		doDealerPhase(table);
		findWinner(table);
		showResultStatus(table);
	}

	@Override
	void findWinner(Table table) {
		int playerMaxPoint = findPlayerMaxPoint(table);
		winnerList = table.getPlayerList().stream()
			.filter(player -> player.getSum() == playerMaxPoint)
			.collect(Collectors.toList());
	}

	private int findPlayerMaxPoint(Table table) {
		return table.getPlayerList().stream()
			.filter(player -> (player.isBust(Rule.getBlackjackPoint()) == false))
			.max(Comparator.comparingInt(player -> (player.sumCardsMax())))
			.get()
			.getSum();
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
			doPlayerPhase(table, player);
		} catch (Exception e) {
			System.out.println(Rule.getOutOfCardsMessage());
		}
	}

	private boolean checkPlayerCondition(Player player) {
		InputController inputController=InputController.getInputController();
		return (player.isBust(Rule.getBlackjackPoint())
			||!inputController.getYesOrNo(player.getName()));
	}

	private void doDealerPhase(Table table) {
		if (checkDealerCondition(table.getDealer())) {
			return;
		}
		try {
			table.drawCards(table.getDealer(), 1);
			showDealerDrawText();
			doDealerPhase(table);
		} catch (Exception e) {
			System.out.println(Rule.getOutOfCardsMessage());
		}
	}

	private boolean checkDealerCondition(Gambler dealer) {
		return (dealer.sumCardsMax() > Rule.getDealerDrawPoint()
			|| dealer.isBust(Rule.getBlackjackPoint()));
	}

	private void showDealerDrawText() {
		OutputController outputController = OutputController.getOutputController();
		outputController.printDealerDrawLine(Rule.getDealerDrawPoint());
		outputController.printNewLine();
	}
}
