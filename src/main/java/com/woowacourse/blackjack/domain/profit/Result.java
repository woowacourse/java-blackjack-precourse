package com.woowacourse.blackjack.domain.profit;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.woowacourse.blackjack.domain.user.dealer.Dealer;
import com.woowacourse.blackjack.domain.user.player.Player;

/**
 * 게임 수행 후 점수를 비교하여 이익률을 계산하는 객체
 *
 * @version 1.0.0
 * @author KSKIM
 * @since 2019-12-15
 */
public class Result {
	private final Dealer dealer;
	private final List<Player> players;

	public Result(Dealer dealer, List<Player> players) {
		this.dealer = Objects.requireNonNull(dealer);
		this.players = Objects.requireNonNull(players);
	}

	public List<Profit> getProfits() {
		List<Profit> profits = getPlayerProfits();
		Profit dealerProfit = getDealerProfit(profits);
		profits.add(0, dealerProfit);
		return profits;
	}

	// 플레이어의 수익을 먼저 계산하고 마지막에 딜러의 수익을 계산하여 리스트 첫 번째에 삽입하기 위해 연결리스트 사용
	private List<Profit> getPlayerProfits() {
		return players.stream()
				.map(player -> new Profit(player.getName(), player.getProfit(player.isWin(dealer))))
				.collect(Collectors.toCollection(LinkedList::new));
	}

	private Profit getDealerProfit(List<Profit> playerProfits) {
		double dealerProfit = playerProfits.stream()
				.mapToDouble(profit -> -profit.getProfit())
				.sum();
		return new Profit(Dealer.NAME, dealerProfit);
	}
}
