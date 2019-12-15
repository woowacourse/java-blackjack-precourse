package application;

import java.util.List;
import java.util.stream.IntStream;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;

public class WinLoseInfoUpdater {
	private static final int BLACKJACK_SCORE = 21;
	
	public static void updateBlackjackInfo(Dealer dealer, List<WinLoseInfo> info, int index) {
		if (dealer.calculateScore() == BLACKJACK_SCORE) {
			info.set(index, WinLoseInfo.DRAW);
		}
		info.set(index, WinLoseInfo.BLACKJACK);
	}
	
	public static void updateLoser(Player player, List<WinLoseInfo> info, int index) {
		if (player.calculateScore() > BLACKJACK_SCORE) {
			info.set(index, WinLoseInfo.LOSE);
		}
	}
	
	public static void updateFinalWinLoseInfo(Dealer dealer, Players players, List<WinLoseInfo> info) {
		if (dealer.calculateScore() > BLACKJACK_SCORE) {
			System.out.println("딜러 파산");		//나중에 outputView로 옮긴다
			IntStream.range(0, info.size())
					.filter(i -> (info.get(i) == WinLoseInfo.UNDETERMINED))
					.forEach(i -> info.set(i, WinLoseInfo.WIN));
		}
		updateUndeterminedInfo(dealer, players, info);
	}
	
	private static void updateUndeterminedInfo(Dealer dealer, Players players, List<WinLoseInfo> info) {
		IntStream.range(0, info.size())
				.filter(i -> (info.get(i) == WinLoseInfo.UNDETERMINED))
				.forEach(i -> info.set(i, decideResult(dealer, players.getPlayerAt(i))));
	}
	
	private static WinLoseInfo decideResult(Dealer dealer, Player player) {
		if (dealer.calculateScore() == player.calculateScore() ) {
			return WinLoseInfo.DRAW;
		}
		return decideWinOrLose(dealer, player);
	}
	
	
	private static WinLoseInfo decideWinOrLose(Dealer dealer, Player player) {
		if (dealer.calculateScore() > player.calculateScore() 
				|| player.calculateScore() > BLACKJACK_SCORE) {
			return WinLoseInfo.LOSE;
		}
		return WinLoseInfo.WIN;
	}
}
