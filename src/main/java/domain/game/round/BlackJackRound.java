package domain.game.round;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import domain.game.Rule;
import domain.game.Table;
import domain.user.Gambler;

public class BlackJackRound extends Round {
	@Override
	void run(Table table) {
		distribute(table, Rule.getBasicDraw());
		findWinner(table);
	}

	@Override
	void findWinner(Table table) {
		winnerList = table.getPlayerList().stream()
			.filter(player -> (player.sumCardsMax() == Rule.getBlackjackPoint()))
			.collect(Collectors.toList());
		if(winnerList.size()>0){
			playerWin=true;
		}
		if(table.getDealer().sumCardsMax()==Rule.getBlackjackPoint()){
			dealerWin=true;
		}
	}

	private void distribute(Table table, int count) {
		try {
			table.drawAll(Arrays.asList(table.getDealer()), count);
			table.drawAll(table.getPlayerList(), count);
		} catch (Exception e) {
			System.out.println(Rule.getOutOfCardsMessage());
		}
	}
}
