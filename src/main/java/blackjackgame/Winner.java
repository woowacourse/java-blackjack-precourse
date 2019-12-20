package blackjackgame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.user.*;
import view.Output;
import blackjackgame.Calculator;

public class Winner {
	private static final int BLACKJACK_SUM = 21;
	private static final boolean BLACKJACK = true;
	private static final boolean NOT_BLACKJACK = false;
	private static final String FINAL_RESULT = "## 최종결과 ##";
	
	private Output output = new Output();
	private Calculator cal = new Calculator();
	
	public List<String> findBlackJack(List<Player> players, Dealer dealer) {
		List<String> blackJackList = new ArrayList<>();
		blackJackList = players.stream()
								.map(player -> player.findBlackJack(BLACKJACK_SUM))
								.filter(s -> s != "")
								.collect(Collectors.toList());
		blackJackList.add(dealer.findBlackJack(BLACKJACK_SUM));
		blackJackList = blackJackList.stream()
									.filter(s -> s != "")
									.collect(Collectors.toList());
		return blackJackList;
	}
	
	public void printBlackJackWinners(List<String> winners, List<Player> players, Dealer dealer) {
		System.out.println(FINAL_RESULT);
		output.finalProfit(dealer, cal.calculateProfit(dealer, winners, BLACKJACK));
		for (Player player : players) {
			output.finalProfit(player, cal.calculateProfit(player, winners, BLACKJACK));
		}
	}
	
	public List<String> findFinalWinners(List<Player> players, Dealer dealer) {
		List<String> winnerList = new ArrayList<>();
		int winningScore = 0;
		winningScore = cal.findWinningScore(winningScore, dealer.sumCardScore());
		for (Player player : players) {
			winningScore = cal.findWinningScore(winningScore, player.sumCardScore());
		}
		final int finalScore = winningScore;
		winnerList = players.stream()
					.filter(player -> player.sumCardScore() == finalScore)
					.map(Player::getName)
					.collect(Collectors.toList());
		if (dealer.sumCardScore() == finalScore) {
			winnerList.add(dealer.getName());
		}
		return winnerList;
	}
	
	public void printFinalWinners(List<String> winners, List<Player> players, Dealer dealer) {
		System.out.println("This is Winners : " + winners);
		output.finalProfit(dealer, (cal.calculateProfit(dealer, winners, NOT_BLACKJACK)));
		for (Player player : players) {
			output.finalProfit(player, (cal.calculateProfit(player, winners, NOT_BLACKJACK)));
		}
	}
}
