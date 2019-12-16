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
		// 정산
	}

	@Override
	void findWinner(Table table) {
		int playerMaxPoint = findPlayerMaxPoint(table);
		winnerList = table.getPlayerList().stream()
			.filter(player -> player.getSum() == playerMaxPoint)
			.collect(Collectors.toList());
		checkDealerWin(table.getDealer(),playerMaxPoint);
		checkPlayerWin();
	}

	private int findPlayerMaxPoint(Table table) {
		return table.getPlayerList().stream()
			.filter(player -> (player.isBust(Rule.getBlackjackPoint()) == false))
			.max(Comparator.comparingInt(player -> (player.sumCardsMax())))
			.orElseGet(()->new Player("",0))
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
			showPlayerDrawText(player);
			doPlayerPhase(table, player);
		} catch (Exception e) {
			System.out.println(Rule.getOutOfCardsMessage());
		}
	}

	private boolean checkPlayerCondition(Player player) {
		if(player.isBust(Rule.getBlackjackPoint())){
			return true;
		}
		InputController inputController = InputController.getInputController();
		boolean yesOrNo=inputController.getYesOrNo(player.getName());
		if(!yesOrNo){
			showPlayerDrawText(player);
		}
		return (!yesOrNo);
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

	private void showPlayerDrawText(Player player){
		OutputController outputController = OutputController.getOutputController();
		outputController.printPlayerCards(player);
		outputController.printNewLine();
	}
}
