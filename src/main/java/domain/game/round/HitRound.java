package domain.game.round;

import java.util.Comparator;
import java.util.stream.Collectors;

import domain.game.Rule;
import domain.game.Table;
import domain.user.Gambler;
import domain.user.Player;

public class HitRound extends Round {
	@Override
	void run(Table table) {
		doPlayersPhase(table,inputController);
		doDealerPhase(table);
		findWinner(table);
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

	private void doPlayersPhase(Table table, InputController inputController) {
		for (Gambler player : table.getPlayerList()
		) {
			doPlayerPhase(table, (Player)player, inputController);
		}
	}

	private void doPlayerPhase(Table table,Player player, InputController inputController) {
		if (player.isBust(Rule.getBlackjackPoint()) || !inputController.getYesOrNo(player.getName())) {
			return;
		}
		try {
			table.drawCards(player, 1);
			doPlayerPhase(table,player, inputController);
		} catch (Exception e) {
			System.out.println(Rule.getOutOfCardsMessage());
		}
	}

	private void doDealerPhase(Table table) {
		if (table.getDealer().sumCardsMax() > Rule.getDealerDrawPoint() || table.getDealer().isBust(Rule.getBlackjackPoint())) {
			return;
		}
		try {
			table.drawCards(table.getDealer(), 1);
			doDealerPhase(table);
		} catch (Exception e) {
			System.out.println(Rule.getOutOfCardsMessage());
		}
	}
}
